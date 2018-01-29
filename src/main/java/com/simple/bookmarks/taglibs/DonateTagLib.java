package com.simple.bookmarks.taglibs;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by jcarretero on 23/01/2018.
 */
public class DonateTagLib extends SimpleTagSupport {

    public DonateTagLib() {}

    @Override
    public void doTag() throws JspException, IOException {
        PageContext pageContext = (PageContext) getJspContext();
        JspWriter out = pageContext.getOut();

        try {
            StringBuffer sb = new StringBuffer();
            sb.append("<form name=\"donate\" action=\"https://www.paypal.com/cgi-bin/webscr\" method=\"post\">\n" +
                    "    <input type=\"hidden\" name=\"business\" value=\"javi_1986@hotmail.com\">\n" +
                    "    <input type=\"hidden\" name=\"cmd\" value=\"_donations\">\n" +
                    "    <input type=\"hidden\" name=\"item_name\" value=\"Simple BookMarks Web\">\n" +
                    "    <input type=\"hidden\" name=\"item_number\" value=\"Donation\">\n" +
                    "    <input type=\"hidden\" name=\"amount\" value=\"25.00\">\n" +
                    "    <input type=\"hidden\" name=\"currency_code\" value=\"GBP\">\n" +
                    "    <img alt=\"\" width=\"1\" height=\"1\" src=\"https://www.paypalobjects.com/en_US/i/scr/pixel.gif\" >\n" +
                    "</form>");
            out.println(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}