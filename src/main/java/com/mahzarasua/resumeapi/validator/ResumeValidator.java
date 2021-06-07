package com.mahzarasua.resumeapi.validator;

import static com.mahzarasua.resumeapi.validator.ValidationUtils.*;

import com.mahzarasua.resumeapi.domain.ResumeRequest;
import com.mahzarasua.resumeapi.domain.ResumeRequest.*;
import com.mahzarasua.resumeapi.exception.ExceptionBody.ErrorDetails;
import com.mahzarasua.resumeapi.model.Resume;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResumeValidator implements Validator<ResumeRequest> {
    private static final String REQ_FIELD = "is a required field";
    private static final String INVALID_FORM = "has an invalid format";

    @Override
    public void validate(ResumeRequest resumeRequest) {
        List<ErrorDetails> errorDetails = new ArrayList<>();
        validateName(resumeRequest.getFirstName(), resumeRequest.getLastName(), errorDetails);
        validateEmail(resumeRequest.getEmail(), errorDetails);
        validatePhoneNumber(resumeRequest.getPhoneNumber(), errorDetails);
        validateCity(resumeRequest.getCity(), errorDetails);
        validateCountry(resumeRequest.getCountry(), errorDetails);
        validateSkills(resumeRequest.getSkills(), errorDetails);
        validateLanguages(resumeRequest.getLanguages(), errorDetails);
        validateWorkExperience(resumeRequest.getWorkexperience(), errorDetails);
        validateEducation(resumeRequest.getEducation(), errorDetails);
        validateChallenges(resumeRequest.getChallenges(), errorDetails);
    }

    private void validateName(String firstName, String lastName, List<ErrorDetails> errorDetails) {
        if (!isValidString(firstName))
            errorDetails.add(newErrorDetail(REQ_FIELD, "First name"));
        if (!isValidString(lastName))
            errorDetails.add(newErrorDetail(REQ_FIELD, "Second name"));
    }

    private void validateEmail(String email, List<ErrorDetails> errorDetails) {
        if (!isValidString(email))
            errorDetails.add(newErrorDetail(REQ_FIELD, "Email"));
        else if (!isValidEmail(email))
            errorDetails.add(newErrorDetail(INVALID_FORM, "Email"));
    }

    private void validatePhoneNumber(String phoneNumber, List<ErrorDetails> errorDetails) {
        if (!isValidString(phoneNumber))
            errorDetails.add(newErrorDetail(REQ_FIELD, "Phone number"));
        else if (!isValidPhoneNumber(phoneNumber))
            errorDetails.add(newErrorDetail(INVALID_FORM, "Phone number"));
    }

    private void validateCity(String city, List<ErrorDetails> errorDetails) {
        if (!isValidString(city))
            errorDetails.add(newErrorDetail(REQ_FIELD, "City"));
    }

    private void validateCountry(String country, List<ErrorDetails> errorDetails) {
        if (!isValidString(country))
            errorDetails.add(newErrorDetail(REQ_FIELD, "Country"));
    }

    private void validateSkills(List<Skill> skills, List<ErrorDetails> errorDetails) {
        // If no skills were added, there are no validations to do here.
        if (skills != null) {
            for (Skill s: skills) {
                if (!isValidString(s.getName()))
                    errorDetails.add(newErrorDetail(REQ_FIELD, "Skill name"));
                else if (s.getPercentage() <= 0 || s.getPercentage() > 100)
                    errorDetails.add(newErrorDetail(
                            INVALID_FORM, "Percentage for skill " + s.getName()));
            }
        }
    }

    private void validateLanguages(List<Language> languages, List<ErrorDetails> errorDetails) {
        // If no languages were added, there are no validations to do here.
        if (languages != null) {
            for (Language l: languages) {
                if (!isValidString(l.getName()))
                    errorDetails.add(newErrorDetail(REQ_FIELD, "Language name"));
                else if (l.getPercentage() <= 0 || l.getPercentage() > 100)
                    errorDetails.add(newErrorDetail(
                            INVALID_FORM, "Percentage for language " + l.getName()));
            }
        }
    }

    private void validateWorkExperience(
            List<WorkExperience> workExperiences, List<ErrorDetails> errorDetails) {
        // If no work experiences were added, there are no validations to do here.
        if (workExperiences != null) {
            for (WorkExperience we: workExperiences) {
                if (!isValidString(we.getTitle()))
                    errorDetails.add(newErrorDetail(REQ_FIELD, "Work title"));
                else if (!isValidString(we.getCompany()))
                    errorDetails.add(newErrorDetail(
                            REQ_FIELD, "Company name for work " + we.getTitle()));
                else if (we.getFrom() == null)
                    errorDetails.add(newErrorDetail(
                            REQ_FIELD, "Beginning date for work " +
                                    we.getTitle() + " at " + we.getCompany()));
                else if (we.getCurrent().equals(false)) {
                    if (we.getTo() == null)
                        errorDetails.add(newErrorDetail(
                                REQ_FIELD, "Ending date for work " +
                                        we.getTitle() + " at " + we.getCompany()));
                        // Verify the ending date is greater than the beginning date.
                    else if (!we.getTo().after(we.getFrom()))
                        errorDetails.add(newErrorDetail(
                                REQ_FIELD, "Ending date for work " +
                                        we.getTitle() + " must be greater than beginning date"));
                }
            }
        }
    }

    private void validateEducation(List<Education> educations, List<ErrorDetails> errorDetails) {
        // If no education is added, there are no validations to do here.
        if (educations != null) {
            for (Education e: educations) {
                if (!isValidString(e.getSchoolName()))
                    errorDetails.add(newErrorDetail(REQ_FIELD, "School name"));
                else if (!isValidString(e.getCareer()))
                    errorDetails.add(newErrorDetail(
                            REQ_FIELD, "Career name at " + e.getSchoolName()));
                else if (!isValidString(e.getDegree()))
                    errorDetails.add(newErrorDetail(
                            REQ_FIELD, "Degree of your career at " + e.getSchoolName()));
                else if (!e.getDegree().equals("Bachelor") && !e.getDegree().equals("Master") && !e.getDegree().equals("Ph"))
                    errorDetails.add(newErrorDetail(
                            INVALID_FORM, "Degree of you career at " + e.getSchoolName() +
                                    " should be either 'Bachelor', 'Master', or 'Ph'"));
                else if (e.getFrom() == null)
                    errorDetails.add(newErrorDetail(
                            REQ_FIELD, "Beginning date of your career at " + e.getSchoolName()));
                else if (e.getTo() == null)
                    errorDetails.add(newErrorDetail(
                            REQ_FIELD, "(Actual or expected) Ending date for your " +
                                    "career at " + e.getSchoolName()));
                else if (!e.getTo().after(e.getFrom()))
                    errorDetails.add(newErrorDetail(
                            INVALID_FORM, "(Actual or expected) Ending date for your " +
                                    "career at " + e.getSchoolName() + " must be greater than beginning date"));
            }
        }
    }

    private void validateChallenges(List<Challenge> challenges, List<ErrorDetails> errorDetails) {
        // If no challenges were added, there are no validations to do here.
        if (challenges != null) {
            for (Challenge c: challenges) {
                if (!isValidString(c.getName()))
                    errorDetails.add(newErrorDetail(REQ_FIELD, "Name of challenge"));
                else if (!isValidString(c.getDescription()))
                    errorDetails.add(newErrorDetail(
                            REQ_FIELD, "Description of challenge " + c.getName()));
                else if (c.getDate() == null)
                    errorDetails.add(newErrorDetail(
                            REQ_FIELD, "Date for completion of challenge " + c.getName()));
            }
        }
    }

    private ErrorDetails newErrorDetail(String errorMessage, String fieldName) {
        ErrorDetails error = new ErrorDetails();
        error.setErrorMessage(errorMessage);
        error.setFieldName(fieldName);
        return error;
    }
}
