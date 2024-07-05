package com.portfolio.service;

import com.portfolio.entity.CertificateTabEntity;
import com.portfolio.entity.ExperienceTabEntity;
import com.portfolio.entity.ProjectTabEntity;
import com.portfolio.entity.UserTabEntity;
import com.portfolio.model.*;
import com.portfolio.repository.CreatePortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    @Autowired
    private CreatePortfolioRepository portfolioRepository;

    @Override
    public PortfolioResponse createPortfolio(PortfolioBody portfolio) {

        List<ExperienceTabEntity> experienceTabEntityList = null;
        List<ProjectTabEntity> projectTabEntityList = null;
        List<CertificateTabEntity> certificateTabEntityList = null;

        if (!portfolio.getExperienceList().isEmpty()) {
            for (Experience experience : portfolio.getExperienceList()) {
                ExperienceTabEntity experienceTabEntity = ExperienceTabEntity.builder()
                        .title(experience.getTitle())
                        .company(experience.getCompany())
                        .fromDate(experience.getFromDate())
                        .toDate(experience.getToDate())
                        .about(experience.getAbout())
                        .skills(experience.getSkills())
                        .build();
                experienceTabEntityList.add(experienceTabEntity);
            }
        }

        if (!portfolio.getProjectList().isEmpty()) {
            for (Project project : portfolio.getProjectList()) {
                ProjectTabEntity projectTabEntity = ProjectTabEntity.builder()
                        .title(project.getTitle())
                        .description(project.getDescription())
                        .live_URL(project.getLiveURL())
                        .github_URL(project.getGithubURL())
                        .skills(project.getSkills())
                        .build();
                projectTabEntityList.add(projectTabEntity);
            }
        }

        if(!portfolio.getCertificationList().isEmpty()) {
            for (Certification certification : portfolio.getCertificationList()) {
                CertificateTabEntity certificateTabEntity = CertificateTabEntity.builder()
                        .name(certification.getName())
                        .certificate_URL(certification.getCertificateURL())
                        .year(certification.getYear())
                        .description(certification.getDescription())
                        .skills(certification.getSkills())
                        .build();
                certificateTabEntityList.add(certificateTabEntity);
            }
        }

        UserTabEntity userTab = UserTabEntity.builder()
                .user_name(portfolio.getUserName())
                .first_name(portfolio.getFirstName())
                .last_name(portfolio.getLastName())
                .middle_name(portfolio.getMiddleName())
                .phone(portfolio.getPhoneNumber())
                .email_id(portfolio.getEmail())
                .linkedIn_URL(portfolio.getLinkedInURL())
                .github_URL(portfolio.getGithubURL())
                .created_on(portfolio.getCreatedOn())
                .experiences(experienceTabEntityList)
                .projects(projectTabEntityList)
                .certifications(certificateTabEntityList)
                .created_on(portfolio.getCreatedOn())
                .build();

        portfolioRepository.save(userTab);
        return null;
    }
}
