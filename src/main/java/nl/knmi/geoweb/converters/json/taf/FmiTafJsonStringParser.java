package nl.knmi.geoweb.converters.json.taf;

import fi.fmi.avi.converter.AviMessageSpecificConverter;
import fi.fmi.avi.converter.ConversionHints;
import fi.fmi.avi.converter.ConversionResult;
import fi.fmi.avi.model.taf.TAF;
import fi.fmi.avi.model.taf.immutable.TAFImpl;

import nl.knmi.geoweb.converters.json.AbstractJsonParser;

/**
 * A simple wrapper to specialize {@link AbstractJSONParser} for TAF.
 */
public class FmiTafJsonStringParser extends AbstractJsonParser implements AviMessageSpecificConverter<String, TAF> {

    /**
     * Converts a JSON TAF message into TAF Object.
     *
     * @param input input message
     * @param hints parsing hints
     * @return the {@link ConversionResult} with the converter message and the possible conversion issues
     */
    @Override
    public ConversionResult<TAF> convertMessage(String input, ConversionHints hints) {
        return doConvertMessage(input, TAF.class, TAFImpl.class, hints);
    }
}
