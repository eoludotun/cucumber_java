package com.CommonAction.Test;

public class Splitting 
{

	public void split()
	{
	/*int i = Integer.parseInt("123");
	 System.out.println("i: " + i);
*/
	 String smartPhones = "Apple IPhone.HTC Evo3D.Nokia N9.LG Optimus.Sony Xperia.Samsung Charge";

			 String[] smartPhonesSplits = smartPhones.split("\\.");


			 for(String smartPhone: smartPhonesSplits){
			 System.out.println(smartPhone);
			 }
			 }


}
