package com.edu.icesi.sigcfp.sigcfpbackendbusiness.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class HelpClassMethod {

	/**
	 * This method let convert the string to format "yyyy-MM-dd" to "dd/MM/yy" that accept the database
	 * @param dateString the current dateString with this format "yyyy-MM-dd"
	 * @return
	 */
	public Date dateFormatThatAcceptDatabase(String dateString) {

		/**
		 *
		 */
		SimpleDateFormat formatter = new SimpleDateFormat("yy-MM-dd");
		SimpleDateFormat formatterOutput = new SimpleDateFormat("dd/MM/yy");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT-5"));
		formatterOutput.setTimeZone(TimeZone.getTimeZone("GMT-5"));

		Date dateResult = null;
		/**
		 * CONVERT TO FORMAT 2021-02-10 to "yy-MM-dd" == "21-02-10"
		 */
		try {
			Date date = formatter.parse(dateString);
			// CONVERT TO FORMAT "yy-MM-dd" to format "dd/MM/yy"
			String databaseFormat = formatterOutput.format(date);
			// System.out.println(databaseFormat);

			/**
			 * CONVERT TO FORMAT "dd/MM/yy" to date
			 */
			dateResult = formatterOutput.parse(databaseFormat);
			// String formattedDateString = formatterOutput.format(dateResult);
			// System.out.println(formattedDateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dateResult;
	}
	
	public  Date convertSecondFormat( Date dateInput) {

        /**
         *
         */
        SimpleDateFormat formatter = new SimpleDateFormat("yy-MM-dd");
        SimpleDateFormat formatterOutput = new SimpleDateFormat("dd/MM/yy");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT-5"));
        formatterOutput.setTimeZone(TimeZone.getTimeZone("GMT-5"));

        /**
         * CONVERT TO FORMAT 2021-02-10 to "yy-MM-dd" == "21-02-10"
         */
        // CONVERT TO FORMAT "yy-MM-dd" to format "dd/MM/yy"
        String databaseFormat = formatterOutput.format(dateInput);
        //System.out.println(databaseFormat);

        /**
         * CONVERT TO FORMAT "dd/MM/yy" to date
         */
        Date dateResult = new Date();
		try {
			dateResult = formatterOutput.parse(databaseFormat);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //String formattedDateString = formatterOutput.format(dateResult);
        //System.out.println(formattedDateString);

        return dateResult;
    }
	

}
