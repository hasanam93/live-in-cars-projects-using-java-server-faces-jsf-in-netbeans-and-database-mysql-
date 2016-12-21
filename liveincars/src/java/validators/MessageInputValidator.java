package validators;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("MessageInputValidator")
public class MessageInputValidator implements Validator {

	ResourceBundle rb = ResourceBundle.getBundle("others/messages");

	private static final int MINLENGTH = 1;
	private static final int MAXLENGTH = 1000;

	@Override
	public void validate(FacesContext facesContext, UIComponent uiComponent,
			Object object) throws ValidatorException {
		if (object instanceof String) {
			int messageLength = ((String) object).length();
			if (messageLength < MINLENGTH || messageLength > MAXLENGTH) {
				String message = MessageFormat.format(
						rb.getString("chatroom.validator.messagelength"), 
						MINLENGTH, MAXLENGTH);
				FacesMessage facesMesssage = new FacesMessage(message);
				throw new ValidatorException(facesMesssage);
			}
		}
	}

}
