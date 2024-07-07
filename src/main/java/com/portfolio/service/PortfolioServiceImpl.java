package com.portfolio.service;

import com.portfolio.entity.CertificateTabEntity;
import com.portfolio.entity.ExperienceTabEntity;
import com.portfolio.entity.ProjectTabEntity;
import com.portfolio.entity.UserTabEntity;
import com.portfolio.model.*;
import com.portfolio.repository.CreatePortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    @Autowired
    private CreatePortfolioRepository portfolioRepository;

    @Override
    @Transactional
    public PortfolioResponse createPortfolio(PortfolioBody portfolio) {

        List<ExperienceTabEntity> experienceTabEntityList = new ArrayList<>();
        List<ProjectTabEntity> projectTabEntityList = new ArrayList<>();
        List<CertificateTabEntity> certificateTabEntityList = new ArrayList<>();

        UserTabEntity userTab = UserTabEntity.builder()
                .user_name(portfolio.getUserName())
                .first_name(portfolio.getFirstName())
                .last_name(portfolio.getLastName())
                .middle_name(portfolio.getMiddleName())
                .phone(portfolio.getPhoneNumber())
                .email_id(portfolio.getEmail())
                .linkedIn_URL(portfolio.getLinkedInURL())
                .github_URL(portfolio.getGithubURL())
                .secret(portfolio.getSecret())
                .created_on(portfolio.getCreatedOn())
                .experiences(experienceTabEntityList)
                .projects(projectTabEntityList)
                .certifications(certificateTabEntityList)
                .created_on(portfolio.getCreatedOn())
                .build();

        if (portfolio.getExperienceList() != null && !portfolio.getExperienceList().isEmpty()) {
            for (Experience experience : portfolio.getExperienceList()) {
                ExperienceTabEntity experienceTabEntity = ExperienceTabEntity.builder()
                        .title(experience.getTitle())
                        .company(experience.getCompany())
                        .fromDate(experience.getFromDate())
                        .toDate(experience.getToDate())
                        .about(experience.getAbout())
                        .skills(experience.getSkills())
                        .userTab(userTab)
                        .build();
                experienceTabEntityList.add(experienceTabEntity);
            }
        }

        if (!portfolio.getProjectList().isEmpty()) {
            for (Project project : portfolio.getProjectList()) {
                ProjectTabEntity projectTabEntity = ProjectTabEntity.builder()
                        .title(project.getTitle())
                        .description(project.getDescription())
                        .live_url(project.getLiveURL())
                        .github_url(project.getGithubURL())
                        .skills(project.getSkills())
                        .userTab(userTab)
                        .build();
                projectTabEntityList.add(projectTabEntity);
            }
        }

        if(!portfolio.getCertificationList().isEmpty()) {
            for (Certification certification : portfolio.getCertificationList()) {
                CertificateTabEntity certificateTabEntity = CertificateTabEntity.builder()
                        .name(certification.getName())
                        .certificate_url(certification.getCertificateURL())
                        .year(certification.getYear())
                        .description(certification.getDescription())
                        .skills(certification.getSkills())
                        .userTab(userTab)
                        .build();
                certificateTabEntityList.add(certificateTabEntity);
            }
        }

        userTab.setExperiences(experienceTabEntityList);
        userTab.setCertifications(certificateTabEntityList);
        userTab.setProjects(projectTabEntityList);
        portfolioRepository.save(userTab);
        return null;
    }

    @Override
    public PortfolioBody getPortfolio(String userName) {
        UserTabEntity userTab = portfolioRepository.findByUserName(userName);
        return buildPortfolioBody(userTab);
    }

    private PortfolioBody buildPortfolioBody(UserTabEntity userTab) {
        List<Experience> experiences = buildExperiences(userTab.getExperiences());
        List<Certification> certifications = buildCertifications(userTab.getCertifications());
        List<Project> projects = buildProjects(userTab.getProjects());
        return PortfolioBody.builder()
                .userId(userTab.getUser_id())
                .userName(userTab.getUser_name())
                .firstName(userTab.getFirst_name())
                .middleName(userTab.getMiddle_name())
                .lastName(userTab.getLast_name())
                .phoneNumber(userTab.getPhone())
                .email(userTab.getEmail_id())
                .githubURL(userTab.getGithub_URL())
                .linkedInURL(userTab.getLinkedIn_URL())
                .experienceList(experiences)
                .projectList(projects)
                .certificationList(certifications)
                .createdOn(userTab.getCreated_on())
                .build();
    }

    private List<Project> buildProjects(List<ProjectTabEntity> projects) {
        List<Project> projectList = new ArrayList<>();
        for(ProjectTabEntity projectTabEntity : projects) {
            Project project = Project.builder()
                    .title(projectTabEntity.getTitle())
                    .description(projectTabEntity.getDescription())
                    .skills(projectTabEntity.getSkills())
                    .githubURL(projectTabEntity.getGithub_url())
                    .liveURL(projectTabEntity.getLive_url())
                    .build();
            projectList.add(project);
        }
        return projectList;
    }

    private List<Certification> buildCertifications(List<CertificateTabEntity> certifications) {
        List<Certification> certificationList = new ArrayList<>();
        for(CertificateTabEntity certificateTabEntity : certifications) {
            Certification certification = Certification.builder()
                    .name(certificateTabEntity.getName())
                    .year(certificateTabEntity.getYear())
                    .description(certificateTabEntity.getDescription())
                    .skills(certificateTabEntity.getSkills())
                    .certificateURL(certificateTabEntity.getCertificate_url())
                    .build();
            certificationList.add(certification);
        }
        return certificationList;
    }

    private List<Experience> buildExperiences(List<ExperienceTabEntity> experiences) {
        List<Experience> experienceList = new ArrayList<>();
        for(ExperienceTabEntity experienceTabEntity : experiences) {
            Experience experience = Experience.builder()
                    .title(experienceTabEntity.getTitle())
                    .company(experienceTabEntity.getCompany())
                    .about(experienceTabEntity.getAbout())
                    .fromDate(experienceTabEntity.getFromDate())
                    .toDate(experienceTabEntity.getToDate())
                    .skills(experienceTabEntity.getSkills())
                    .build();
            experienceList.add(experience);
        }
        return experienceList;
    }

    @Override
    public PortfolioResponse deletePortfolio(String userName, DeletePortfolio deletePortfolio) {
        UserTabEntity userTab = portfolioRepository.findByUserNameWithUserIdSecret(userName, deletePortfolio.getUserId(), deletePortfolio.getSecret());
        PortfolioResponse portfolioResponse = new PortfolioResponse();
        if (userTab != null) {
            portfolioRepository.deleteById(userTab.getUser_id());
            portfolioResponse.setUserID(userTab.getUser_id());
            portfolioResponse.setUserName(userTab.getUser_name());
            portfolioResponse.setMessage("User Portfolio Deleted");
            portfolioResponse.setStatusCode(HttpStatus.OK);
            return portfolioResponse;
        }
        portfolioResponse.setUserName(userName);
        portfolioResponse.setMessage("User Portfolio Not Found");
        portfolioResponse.setStatusCode(HttpStatus.NOT_FOUND);
        return portfolioResponse;
    }
}
