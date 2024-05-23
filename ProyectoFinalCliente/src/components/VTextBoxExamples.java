package components;

import javax.swing.ImageIcon;

import validation.Formatos;
import validation.Validaciones;
import validation.ValidationObject;

public class VTextBoxExamples {

	public static VTextBox getOnlyText() {
		VTextBox vtb = new VTextBox();

		vtb.setValidationFunc(text -> {
			if (!Validaciones.ValidarLargo(text, 20)) {
				return new ValidationObject(
						"El campo es obligaotrio (no puede ser vacio) y debe tener un maximo de 20");
			}

			if (!Validaciones.ValidarSoloLetras(text, true)) {
				return new ValidationObject("El campo solo puede contener letras y espacios");
			}
			return ValidationObject.VALID;
		});

		vtb.setErrorIcon(new ImageIcon(VTextBoxExamples.class.getResource("/components/error2.png")));

		vtb.setFormmatedFunc(text -> {
			return Formatos.ToTitle(text.trim());
		});

		return vtb;
	}
}
