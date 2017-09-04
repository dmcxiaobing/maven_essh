package com.david.mavenessh.utils;
import java.text.DateFormat;     
import java.text.ParseException;     
import java.text.SimpleDateFormat;     
import java.util.Date;     
import java.util.Map;     
    
import org.apache.log4j.Logger;     
import org.apache.struts2.util.StrutsTypeConverter;    

public class StrutsDateConverter extends StrutsTypeConverter{
	 private static final Logger log = Logger.getLogger(DateConverter.class);     
	    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";     
	         
	    public static final DateFormat[] ACCEPT_DATE_FORMATS = {     
	            new SimpleDateFormat(DEFAULT_DATE_FORMAT),     
	            new SimpleDateFormat("yyyy年MM月dd日"),     
	            new SimpleDateFormat("yyyy/MM/dd") };     
	    
	     
	    public StrutsDateConverter() {     
	    }     
	    
	      
	    public Object convertFromString(Map context, String[] values, Class toClass) {     
	        if (values[0] == null || values[0].trim().equals(""))     
	            return null;     
	        for (DateFormat format : ACCEPT_DATE_FORMATS) {     
	            try {     
	                return format.parse(values[0]);     
	            } catch (ParseException e) {     
	                continue;     
	            } catch (RuntimeException e) {     
	                continue;     
	            }     
	        }     
	        log.debug("can not format date string:" + values[0]);     
	        return null;     
	    }     
	    
	    public String convertToString(Map context, Object o) {     
	        if (o instanceof Date) {     
	            SimpleDateFormat format = new SimpleDateFormat(     
	                    DEFAULT_DATE_FORMAT);     
	            try {     
	                return format.format((Date) o);     
	            } catch (RuntimeException e) {     
	                return "";     
	            }     
	        }     
	        return "";     
	    }     
	    
	  
	}  
