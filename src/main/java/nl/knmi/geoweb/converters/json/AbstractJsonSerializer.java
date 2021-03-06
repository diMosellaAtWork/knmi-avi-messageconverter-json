package nl.knmi.geoweb.converters.json;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import fi.fmi.avi.converter.ConversionHints;
import fi.fmi.avi.converter.ConversionIssue;
import fi.fmi.avi.converter.ConversionResult;
import fi.fmi.avi.model.AviationWeatherMessage;

public abstract class AbstractJsonSerializer {

    /**
     * @param input the message POJO to convert
     * @param hints hints to guide the conversion.
     * @return the result of the conversion
     */
    protected ConversionResult<String> doConvertMessage(AviationWeatherMessage input, ConversionHints hints) {
        ConversionResult<String> result = new ConversionResult<>();
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new Jdk8Module());
        om.registerModule(new JavaTimeModule());
        ObjectWriter writer = om.writerWithDefaultPrettyPrinter();
        StringWriter sw = new StringWriter();
        try {
            writer.writeValue(sw, input);
            result.setConvertedMessage(sw.toString());
            result.setStatus(ConversionResult.Status.SUCCESS);
        } catch (IOException e) {
            result.addIssue(new ConversionIssue(ConversionIssue.Severity.ERROR, ConversionIssue.Type.OTHER,"Error in serializing to JSON", e));
            result.setStatus(ConversionResult.Status.FAIL);
        }
        return result;
    }
}