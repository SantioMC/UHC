package me.santio.uhc.models;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.WordUtils;

@SuppressWarnings("unused")
public class ScenarioData {
    @Setter @Getter private String description;
    @Setter @Getter private Object value = null;
    @Getter private final String key;
    
    public ScenarioData(String key, String description) {
        this.key = key;
        this.description = description;
    }
    
    public ScenarioData(String key, String description, Object value) {
        this.key = key;
        this.description = description;
        this.value = value;
    }
    
    public String[] getWrappedDescription() {
        return WordUtils.wrap(getDescription(), 30).split("\n");
    }
}
