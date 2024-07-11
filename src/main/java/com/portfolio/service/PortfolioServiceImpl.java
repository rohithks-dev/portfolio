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
import java.util.Optional;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    @Autowired
    private CreatePortfolioRepository portfolioRepository;

    @Override
    @Transactional
    public PortfolioResponse createPortfolio(PortfolioBody portfolio) {
        UserTabEntity userTab = buildUserTabEntity(portfolio);
        List<ExperienceTabEntity> experienceTabEntityList = buildExperienceTabEntities(portfolio, userTab);
        List<ProjectTabEntity> projectTabEntityList = buildProjectTabEntities(portfolio, userTab);
        List<CertificateTabEntity> certificateTabEntityList = buildCertificateTabEntities(portfolio, userTab);
        userTab.setExperiences(experienceTabEntityList);
        userTab.setCertifications(certificateTabEntityList);
        userTab.setProjects(projectTabEntityList);
        UserTabEntity userTabEntity = portfolioRepository.save(userTab);
        return PortfolioResponse.builder()
                .userID(userTabEntity.getUser_id())
                .userName(userTabEntity.getUser_name())
                .message("User Portfolio Created")
                .statusCode(HttpStatus.CREATED)
                .build();
    }

    private List<CertificateTabEntity> buildCertificateTabEntities(PortfolioBody portfolio, UserTabEntity userTab) {
        List<CertificateTabEntity> certificateTabEntityList = new ArrayList<>();
        if (portfolio.getCertificationList() != null && !portfolio.getCertificationList().isEmpty()) {
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
        return certificateTabEntityList;
    }

    private List<ProjectTabEntity> buildProjectTabEntities(PortfolioBody portfolio, UserTabEntity userTab) {
        List<ProjectTabEntity> projectTabEntityList = new ArrayList<>();
        if (portfolio.getProjectList() != null && !portfolio.getProjectList().isEmpty()) {
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
        return projectTabEntityList;
    }

    private List<ExperienceTabEntity> buildExperienceTabEntities1(PortfolioBody portfolio, UserTabEntity userTab) {
        List<ExperienceTabEntity> experienceTabEntityList = new ArrayList<>();
        if (portfolio.getExperienceList() != null && !portfolio.getExperienceList().isEmpty()) {
            for (Experience experience : portfolio.getExperienceList()) {
                ExperienceTabEntity experienceTabEntity = new ExperienceTabEntity();
                experienceTabEntity.setTitle(experience.getTitle());
                experienceTabEntity.setCompany(experience.getCompany());
                experienceTabEntity.setFromDate(experience.getFromDate());
                experienceTabEntity.setToDate(experience.getToDate());
                experienceTabEntity.setAbout(experience.getAbout());
                experienceTabEntity.setSkills(experience.getSkills());
                experienceTabEntity.setUserTab(userTab);
                experienceTabEntityList.add(experienceTabEntity);
            }
        }
        return experienceTabEntityList;
    }

    private List<ExperienceTabEntity> buildExperienceTabEntities(PortfolioBody portfolio, UserTabEntity userTab) {
        List<ExperienceTabEntity> experienceTabEntityList = new ArrayList<>();
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
        return experienceTabEntityList;
    }

    private UserTabEntity buildUserTabEntity(PortfolioBody portfolio) {
        return UserTabEntity.builder()
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
                .build();
    }

    @Override
    @Transactional
    public PortfolioBody getPortfolio(String userName) {
        UserTabEntity userTab = portfolioRepository.findByUserName(userName);
        if(userTab != null) {
            return buildPortfolioBody(userTab);
        }
        return PortfolioBody.builder()
                .message("User not found")
                .statusCode(HttpStatus.NOT_FOUND)
                .build();

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
    public PortfolioResponse verifyPortfolio(Integer userId, String userName, String secret) {
        UserTabEntity userTab = portfolioRepository.findByUserNameWithUserIdSecret(userName, userId, secret);
        if (userTab != null) {
            return PortfolioResponse.builder()
                    .userName(userName)
                    .userID(userId)
                    .message("User Portfolio Verified")
                    .statusCode(HttpStatus.OK)
                    .build();
        }
        return userPortfolioNotFound(userName);
    }

    public PortfolioResponse userPortfolioNotFound(String userName) {
        return PortfolioResponse.builder()
                .userName(userName)
                .message("User Portfolio Not Found")
                .statusCode(HttpStatus.NOT_FOUND)
                .build();
    }

    @Override
    public PortfolioResponse updatePortfolio(String userName, PortfolioBody portfolioBody) {
        UserTabEntity userTab = portfolioRepository.findByUserName(userName);
        if (userTab != null) {
            updateUserTabEntity(userTab, portfolioBody);
            buildExperienceTabEntities1(portfolioBody, userTab);
            buildCertificateTabEntities(portfolioBody, userTab);
            buildProjectTabEntities(portfolioBody, userTab);
            portfolioRepository.save(userTab);
            return PortfolioResponse.builder()
                    .userName(userName)
                    .message("User Portfolio Updated")
                    .statusCode(HttpStatus.OK)
                    .build();
        }
        return userPortfolioNotFound(userName);
    }

    private void updateUserTabEntity(UserTabEntity userTab, PortfolioBody portfolioBody) {
        if(portfolioBody.getFirstName() != null) {
            userTab.setFirst_name(portfolioBody.getFirstName());
        }
        Optional.ofNullable(portfolioBody.getFirstName())
                        .ifPresent(userTab::setFirst_name);
        Optional.ofNullable(portfolioBody.getLastName())
                .ifPresent(userTab::setLast_name);
        Optional.ofNullable(portfolioBody.getPhoneNumber())
                .ifPresent(userTab::setPhone);
        Optional.ofNullable(portfolioBody.getEmail())
                .ifPresent(userTab::setEmail_id);
        Optional.ofNullable(portfolioBody.getLinkedInURL())
                .ifPresent(userTab::setLinkedIn_URL);
        Optional.ofNullable(portfolioBody.getGithubURL())
                .ifPresent(userTab::setGithub_URL);
    }

    @Override
    @Transactional
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
        return userPortfolioNotFound(userName);
    }
}
