package validators;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("UserNameValidator")
public class UserNameValidator implements Validator {

	ResourceBundle rb = ResourceBundle.getBundle("others/messages");

	private static final int MINLENGTH = 2;
	private static final int MAXLENGTH = 25;

	@Override
	public void validate(FacesContext facesContext, UIComponent uiComponent,
			Object object) throws ValidatorException {
		if (object instanceof String) {
			int userNameLength = ((String) object).length();
			if (userNameLength < MINLENGTH || userNameLength > MAXLENGTH) {
				String message = MessageFormat.format(
						rb.getString("welcome.validator.usernamelength"),
						MINLENGTH, MAXLENGTH);
				FacesMessage facesMesssage = new FacesMessage(message);
				throw new ValidatorException(facesMesssage);
			}
		}
	}

}
