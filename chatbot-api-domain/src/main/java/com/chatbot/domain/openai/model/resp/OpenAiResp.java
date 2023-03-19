package com.chatbot.domain.openai.model.resp;

import com.chatbot.domain.openai.model.vo.Choice;
import com.chatbot.domain.openai.model.vo.Usage;

import java.util.List;

public class OpenAiResp {
    private String id;

    private String object;

    private int created;

    private String model;

    private List<Choice> choices;

    private Usage usage;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getObject() {
        return this.object;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public int getCreated() {
        return this.created;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return this.model;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    public Usage getUsage() {
        return this.usage;
    }
}
