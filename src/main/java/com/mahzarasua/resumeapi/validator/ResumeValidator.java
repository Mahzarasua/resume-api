package com.mahzarasua.resumeapi.validator;

import com.mahzarasua.resumeapi.domain.ResumeRequest;
import com.mahzarasua.resumeapi.domain.ResumeRequest.*;
import com.mahzarasua.resumeapi.exception.ExceptionBody.ErrorDetails;
import com.mahzarasua.resumeapi.exception.MissingRequiredFieldException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.mahzarasua.resumeapi.validator.ValidationUtils.*;

@Service
public class ResumeValidator implements Validator<ResumeRequest> {
    private static final String REQ_FIELD = "is a required field";
    private static final String INVALID_FORM = "has an invalid format";

    @Override
    public void validate(ResumeRequest resumeRequest) {
        List<ErrorDetails> errorDetails = new ArrayList<>();
        validateRequiredField(resumeRequest, errorDetails);

        if (!errorDetails.isEmpty())
            throw new MissingRequiredFieldException(errorDetails, "One or more fields are required or the format is invalid");
    }

    private void validateRequiredField(ResumeRequest resumeRequest, List<ErrorDetails> errorDetails) {
        if (!isValidString(resumeRequest.getFirstName())) errorDetails.add(newErrorDetail(REQ_FIELD, "First name"));
        if (!isValidString(resumeRequest.getLastName())) errorDetails.add(newErrorDetail(REQ_FIELD, "Second name"));
        if (!isValidString(resumeRequest.getEmail())) errorDetails.add(newErrorDetail(REQ_FIELD, "Email"));
        if (!isValidString(resumeRequest.getPhoneNumber())) errorDetails.add(newErrorDetail(REQ_FIELD, "Phone number"));
        if (!isValidString(resumeRequest.getCity())) errorDetails.add(newErrorDetail(REQ_FIELD, "City"));
        if (!isValidString(resumeRequest.getCountry())) errorDetails.add(newErrorDetail(REQ_FIELD, "Country"));
        if (!isValidEmail(resumeRequest.getEmail())) errorDetails.add(newErrorDetail(INVALID_FORM, "Email"));
        if (!isValidPhoneNumber(resumeRequest.getPhoneNumber()))
            errorDetails.add(newErrorDetail(INVALID_FORM, "Phone number"));

        validateSkills(resumeRequest, errorDetails);
        validateLanguages(resumeRequest, errorDetails);
        validateWorkExperience(resumeRequest, errorDetails);
        validateEducation(resumeRequest, errorDetails);
        validateChallenges(resumeRequest, errorDetails);
    }

    private void validateSkills(ResumeRequest resumeRequest, List<ErrorDetails> errorDetails) {
        // If no resumeRequest were added, there are no validations to do here.
        if (resumeRequest.getSkills() != null && !resumeRequest.getSkills().isEmpty()) {
            for (Skill s : resumeRequest.getSkills()) {
                if (!isValidString(s.getName())) errorDetails.add(newErrorDetail(REQ_FIELD, "Skill name"));
                if (s.getPercentage() <= 0 || s.getPercentage() > 100)
                    errorDetails.add(newErrorDetail(INVALID_FORM, "Percentage for skill " + s.getName()));
            }
        }
    }

    private void validateLanguages(ResumeRequest resumeRequest, List<ErrorDetails> errorDetails) {
        // If no resumeRequest were added, there are no validations to do here.
        if (resumeRequest.getLanguages() != null && !resumeRequest.getLanguages().isEmpty())
            for (Language l : resumeRequest.getLanguages()) {
                if (!isValidString(l.getName())) errorDetails.add(newErrorDetail(REQ_FIELD, "Language name"));
                if (l.getPercentage() <= 0 || l.getPercentage() > 100)
                    errorDetails.add(newErrorDetail(REQ_FIELD, "Percentage for language " + l.getName()));
            }
    }

    private void validateWorkExperience(ResumeRequest resumeRequest, List<ErrorDetails> errorDetails) {
        // If no work experiences were added, there are no validations to do here.
        if (resumeRequest.getWorkexperience() != null && !resumeRequest.getWorkexperience().isEmpty()) {
            for (WorkExperience we : resumeRequest.getWorkexperience()) {
                if (!isValidString(we.getTitle())) errorDetails.add(newErrorDetail(REQ_FIELD, "Work title"));
                else if (!isValidString(we.getCompany()))
                    errorDetails.add(newErrorDetail(REQ_FIELD, "Company name for work " + we.getTitle()));
                else if (we.getFrom() == null)
                    errorDetails.add(newErrorDetail(REQ_FIELD, "Beginning date for work " + we.getTitle() + " at " + we.getCompany()));
                else if (we.getCurrent().equals(false)) {
                    if (we.getTo() == null)
                        errorDetails.add(newErrorDetail(REQ_FIELD, "Ending date for work " + we.getTitle() + " at " + we.getCompany()));
                        // Verify the ending date is greater than the beginning date.
                    else if (!we.getTo().after(we.getFrom()))
                        errorDetails.add(newErrorDetail(REQ_FIELD, "Ending date for work " + we.getTitle() + " must be greater than beginning date"));
                }
            }
        }
    }

    private void validateEducation(ResumeRequest resumeRequest, List<ErrorDetails> errorDetails) {
        // If no education is added, there are no validations to do here.
        if (resumeRequest.getEducation() != null && !resumeRequest.getEducation().isEmpty()) {
            for (Education e : resumeRequest.getEducation()) {
                if (!isValidString(e.getSchoolName())) errorDetails.add(newErrorDetail(REQ_FIELD, "School name"));
                else if (!isValidString(e.getCareer()))
                    errorDetails.add(newErrorDetail(REQ_FIELD, "Career name at " + e.getSchoolName()));
                else if (!isValidString(e.getDegree()))
                    errorDetails.add(newErrorDetail(REQ_FIELD, "Degree of your career at " + e.getSchoolName()));
                else if (!e.getDegree().equals("Bachelor") && !e.getDegree().equals("Master") && !e.getDegree().equals("Ph"))
                    errorDetails.add(newErrorDetail(INVALID_FORM, "Degree of you career at " + e.getSchoolName() + " should be either 'Bachelor', 'Master', or 'Ph'"));
                else if (e.getFrom() == null)
                    errorDetails.add(newErrorDetail(REQ_FIELD, "Beginning date of your career at " + e.getSchoolName()));
                else if (e.getTo() == null)
                    errorDetails.add(newErrorDetail(REQ_FIELD, "(Actual or expected) Ending date for your " + "career at " + e.getSchoolName()));
                else if (!e.getTo().after(e.getFrom()))
                    errorDetails.add(newErrorDetail(INVALID_FORM, "(Actual or expected) Ending date for your " + "career at " + e.getSchoolName() + " must be greater than beginning date"));
            }
        }
    }

    private void validateChallenges(ResumeRequest resumeRequest, List<ErrorDetails> errorDetails) {
        // If no resumeRequest were added, there are no validations to do here.
        if (resumeRequest.getChallenges() != null && !resumeRequest.getChallenges().isEmpty()) {
            for (Challenge c : resumeRequest.getChallenges()) {
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
