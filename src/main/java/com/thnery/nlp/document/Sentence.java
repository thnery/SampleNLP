package com.thnery.nlp.document;

import edu.stanford.nlp.trees.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Sentence {
    private int id;

    private String content;
    private List<Token> tokens;
    private List<Token> tokensWithoutStopWords;

    private Set<Chunking> chunkings;
    private Set<NEREntity> nerEntities;

    private Tree syntacticTree;
    private List<DependencyRelation> dependecyRelations;

    public Sentence() {
    }

    public Sentence(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Set<Chunking> getChunkings() {
        return chunkings;
    }

    public void setChunkings(Set<Chunking> chunkings) {
        this.chunkings = chunkings;
    }

    public Set<NEREntity> getNerEntities() {
        return nerEntities;
    }

    public void setNerEntities(Set<NEREntity> nerEntities) {
        this.nerEntities = nerEntities;
    }

    public Tree getSyntacticTree() {
        return syntacticTree;
    }

    public void setSyntacticTree(Tree syntacticTree) {
        this.syntacticTree = syntacticTree;
    }

    public List<DependencyRelation> getDependecyRelations() {
        return dependecyRelations;
    }

    public void setDependecyRelations(List<DependencyRelation> dependecyRelations) {
        this.dependecyRelations = dependecyRelations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean addTokenWithoutStopWords(Token token) {
        if (this.tokensWithoutStopWords == null) {
            this.tokensWithoutStopWords = new ArrayList<Token>();
        }

        return this.tokensWithoutStopWords.add(token);
    }

    public boolean addToken(Token token) {
        if (this.tokens == null) {
            this.tokens = new ArrayList<Token>();
        }

        return this.tokens.add(token);
    }
}
