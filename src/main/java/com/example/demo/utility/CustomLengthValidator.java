
package com.example.demo.utility;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomLengthValidator implements ConstraintValidator<LengthConstraint, String>
{
	@Override
	public void initialize(LengthConstraint arg0)
	{

	}


	@Override
	public boolean isValid(String name, ConstraintValidatorContext ctx)
	{

		if (name == null)
		{
			return false;
		}
		if ((name.length() < 3) || (name.length() > 10))
		{
			return false;
		}
		return true;
	}
}
