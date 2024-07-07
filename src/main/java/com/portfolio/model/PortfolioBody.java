    package com.portfolio.model;

    import com.fasterxml.jackson.annotation.JsonInclude;
    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Data;
    import org.springframework.http.HttpStatus;

    import java.time.LocalDateTime;
    import java.util.List;

    @Data
    @Builder
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public class PortfolioBody {

        private String userName;
        private Integer userId;
        private String firstName;
        private String middleName;
        private String lastName;
        private Integer phoneNumber;
        private String email;
        //private photo;
        private String linkedInURL;
        private String githubURL;
        private String secret;
        //private resume
        private List<Experience> experienceList;
        private List<Project> projectList;
        private List<Certification> certificationList;
        private LocalDateTime createdOn;
        private String message;
        private HttpStatus statusCode;
    }
