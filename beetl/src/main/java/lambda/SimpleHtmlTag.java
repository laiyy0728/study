package lambda;

import org.beetl.core.Tag;

import java.util.Map;

/**
 * Created by laiyy
 * Date 2017/7/11.
 */
public class SimpleHtmlTag extends Tag {

    @Override
    public void render() {

        String tagName = (String)this.args[0];
        Map attr = (Map)args[1];
        String value = (String)attr.get("attr");
        try {
            this.ctx.byteWriter.writeString(value);
        }catch (Exception e){
            e.printStackTrace();

        }

    }
}
