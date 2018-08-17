import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by laiyy
 * Date 2017/9/21.
 */
@SuppressWarnings("unchecked")
public class Test {


    public static void main(String[] args) {

        String input = "<#kidNewsList channelId=\"2038\" start=\"2\" size=\"1\" siteId=\"123456\" ;list>  <% for (news in list) { %>${title(news!)} <% } %></#kidNewsList>";
//        String input = "${title(news!)}";

        int channelId = 2018;
        int start = 1;
        int size = 2;

        String tag = "picNews";

        String channelReg = "channelId=\"[0-9]{3,5}\"";
        String startReg = "start=\"[0-9]{1,2}\"";
        String sizeReg = "size=\"[0-9]{1,2}\"";
//        String tagReg = "^<#[a-z][a-z]+";

//        System.out.println(Pattern.compile(tagReg).matcher(input).find());

//        Pattern compile = Pattern.compile(tagReg);
//        Matcher matcher = compile.matcher(input);
//        System.out.println(matcher.find());
//        System.out.println(input.matches(tagReg));

        input = input.replaceAll(channelReg, "channelId=\"" + channelId + "\"");
        input = input.replaceAll(startReg, "start=\"" + start + "\"");
        input = input.replaceAll(sizeReg, "size=\"" + size+ "\"");
        String replace = StringUtils.substringBefore(input, " ");
        input = input.replaceAll(replace, "<#" + tag + " ");
//
        System.out.println(input);


//        String txt="<#kidNewsList channelId";
//
//        String re1=".*?";	// Non-greedy match on filler
//        String re2="((?:[a-z][a-z]+))";	// Word 1
//
//        Pattern p = Pattern.compile(re1+re2,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
//        Matcher m = p.matcher(txt);
//        if (m.find())
//        {
//            String word1=m.group(1);
//            System.out.print("("+word1.toString()+")"+"\n");
//        }

        String test = "zhangsan/";

        System.out.println(StringUtils.substringBefore(test, "/"));
    }


}
