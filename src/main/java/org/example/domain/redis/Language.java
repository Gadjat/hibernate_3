package org.example.domain.redis;

import java.math.BigDecimal;

public class Language {
    private String language;
    private Boolean isOfficial;
    private BigDecimal percentage;

    public String getLanguage() {
        return language;
    }

    public Boolean getOfficial() {
        return isOfficial;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setOfficial(Boolean official) {
        isOfficial = official;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }
}
