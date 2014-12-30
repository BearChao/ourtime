package org.zynick.ourtime.ourtime;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zynick on 2014/12/30.
 */
public class PullEventParser implements EventParser{
    @Override
    public List<event> parse(InputStream is) throws Exception {
        List<event> events = null;
        event event = null;
        XmlPullParser parser = Xml.newPullParser();//由xml创建一个XmlPullParser实例
        parser.setInput(is,"UTF-8");//设置输入流

        int eventType = parser.getEventType();;
        while(eventType != XmlPullParser.END_DOCUMENT){
            switch(eventType){
                case XmlPullParser.START_DOCUMENT:
                    events = new ArrayList<event>();
                    break;
                case XmlPullParser.START_TAG:
                    if (parser.getName().equals("event")){
                        event = new event();
                    } else if (parser.getName().equals("name")){
                        eventType = parser.next();
                        event.setName(parser.getText());
                    } else if (parser.getName().equals("date")){
                        eventType = parser.next();
                        event.setDate(parser.getText());
                    } else if (parser.getName().equals("loop")){
                        eventType = parser.next();
                        event.setLoop(Boolean.parseBoolean(parser.getText()));
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if (parser.getName().equals("event")){
                        events.add(event);
                        event = null;
                    }
                    break;
            }
            eventType = parser.next();
        }
        return events;
    }

    @Override
    public String serialize(List<event> events) throws Exception {
        XmlSerializer serializer = Xml.newSerializer();//创建XmlSerializer实例
        StringWriter writer = new StringWriter();
        serializer.setOutput(writer);
        serializer.startDocument("UTF-8",true);
        serializer.startTag("","events");
        for(event event : events){
            serializer.startTag("","event");
            //serializer.attribute("","name",event.getName()+"");
            serializer.startTag("","date");
            serializer.text(event.getName());
            serializer.endTag("","name");

            serializer.startTag("","date");
            serializer.text(event.getDate());
            serializer.endTag("","date");

            serializer.startTag("","loop");
            serializer.text(String.valueOf(event.getLoop()));
            serializer.endTag("","loop");

            serializer.endTag("","event");
        }
        serializer.endTag("","events");
        serializer.endDocument();
        return writer.toString();
    }
}
