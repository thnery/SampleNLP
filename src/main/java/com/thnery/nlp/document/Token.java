package com.thnery.nlp.document;

public class Token {
    private long id;

    private String content;
    private String stemming;

    private int startPosition;
    private int endPosition;
    private boolean isStopWord;

    private String posClass;
    private String type;
    private String ner;

    private boolean isPartNER;
    private long idSentence;

    public Token() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStemming() {
        return stemming;
    }

    public void setStemming(String stemming) {
        this.stemming = stemming;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    public int getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(int endPosition) {
        this.endPosition = endPosition;
    }

    public boolean isStopWord() {
        return isStopWord;
    }

    public void setStopWord(boolean stopWord) {
        isStopWord = stopWord;
    }

    public String getPosClass() {
        return posClass;
    }

    public void setPosClass(String posClass) {
        this.posClass = posClass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNer() {
        return ner;
    }

    public void setNer(String ner) {
        this.ner = ner;
    }

    public boolean isPartNER() {
        return isPartNER;
    }

    public void setPartNER(boolean partNER) {
        isPartNER = partNER;
    }

    public long getIdSentence() {
        return idSentence;
    }

    public void setIdSentence(long idSentence) {
        this.idSentence = idSentence;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
