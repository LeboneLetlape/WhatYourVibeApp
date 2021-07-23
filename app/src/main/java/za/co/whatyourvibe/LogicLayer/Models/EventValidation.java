package za.co.whatyourvibe.LogicLayer.Models;

public class EventValidation {
    public boolean IsTitleValid;
    public boolean IsCategoryValid;
    public boolean IsDescriptionValid;
    public boolean IsLocationValid;
    public boolean IsDateValid;
    public boolean IsTimeValid;
    public boolean IsTicketValid;
    public boolean IsRestrictionValid;
    public boolean IsPhotoValid;
    public boolean IsMainPhotoValid;
    public boolean IsVideoValid;
    public boolean IsContactValid;
    public boolean IsSocialMediaValid;
    
    
    public boolean IsValid()
    {
        return (IsTitleValid
        && IsCategoryValid
        && IsDescriptionValid
        && IsLocationValid
        && IsDateValid
        && IsTimeValid
        && IsTicketValid
        && IsRestrictionValid
        && IsPhotoValid
        && IsMainPhotoValid
        && IsVideoValid
        && IsContactValid
        && IsSocialMediaValid);
    }
    
    public String ValidationMessage()
    {
        String _StringValidationMessage = "Please validate the following section of the event:";

        if(!IsTitleValid)
            _StringValidationMessage += "Title;";

        if(!IsCategoryValid)
            _StringValidationMessage += "Category;";

        if(!IsLocationValid)
            _StringValidationMessage += "Location;";

        if(!IsDateValid)
            _StringValidationMessage += "Date;";

        if(!IsTimeValid)
            _StringValidationMessage += "Time;";

        if(!IsTicketValid)
            _StringValidationMessage += "Ticket;";

        if(!IsRestrictionValid)
            _StringValidationMessage += "Restriction;";

        if(!IsPhotoValid)
            _StringValidationMessage += "Photos;";


        if(!IsMainPhotoValid)
            _StringValidationMessage += "Main Image;";

        if(!IsVideoValid)
            _StringValidationMessage += "Video;";

        if(!IsContactValid)
            _StringValidationMessage += "Contact;";

        if(!IsSocialMediaValid)
            _StringValidationMessage += "Social Media;";


        return _StringValidationMessage;
    }
}
