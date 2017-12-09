package com.example.chris.memegenerator.exceptions;

public class ZipcodeNotRecognizedException extends Exception
{
	public ZipcodeNotRecognizedException() 
	{
        super("Zipcode must be 5 digits.");
    }
}
