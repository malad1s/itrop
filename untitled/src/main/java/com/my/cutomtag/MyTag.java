package com.my.cutomtag;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.Calendar;
import javax.servlet.jsp.JspException;

public class MyTag extends TagSupport {

    public int doStartTag() throws JspException {
        JspWriter out=pageContext.getOut();//returns the instance of JspWriter
        try{
            out.print("name111111");//printing date and time using JspWriter
        }catch(Exception e){System.out.println(e);}
        return SKIP_BODY;//will not evaluate the body content of the tag
    }
}
