package com.thnery.nlp.document;

import java.util.List;

public class Document {
    private String text;

    private List<Paragraph> paragraphs;
    private List<Sentence> sentences;
    private List<Token> tokens;
    private List<Token> tokensWithoutStopWords;
    private List<Conference> conferences;

    public Document(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Paragraph> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(List<Paragraph> paragraphs) {
        this.paragraphs = paragraphs;
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    public void setSentences(List<Sentence> sentences) {
        this.sentences = sentences;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public List<Token> getTokensWithoutStopWords() {
        return tokensWithoutStopWords;
    }

    public void setTokensWithoutStopWords(List<Token> tokensWithoutStopWords) {
        this.tokensWithoutStopWords = tokensWithoutStopWords;
    }

    public List<Conference> getConferences() {
        return conferences;
    }

    public void setConferences(List<Conference> conferences) {
        this.conferences = conferences;
    }
}
