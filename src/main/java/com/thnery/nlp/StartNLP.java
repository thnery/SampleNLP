package com.thnery.nlp;

import com.thnery.nlp.document.Document;
import com.thnery.nlp.document.Sentence;
import com.thnery.nlp.document.Token;
import com.thnery.nlp.utils.Utils;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.io.File;
import java.util.*;

public class StartNLP {

    private StanfordCoreNLP stanfordCoreNLP;
    private final Set<String> stopList;

    public StartNLP() {
        stopList = new HashSet<String>();
        File file = new File("src/main/resources/StopList.txt");

        try {
            stopList.addAll(Utils.loadSetOfWords(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void  main(String[] args) {
        StartNLP nlp = new StartNLP();
        nlp.start();
    }

    private void start() {
        Properties properties = new Properties();
//        properties.put("annotators", "tokenize, ssplit, pos, lemma, ner, parse, depparse, coref, kbp, quote");
        properties.put("annotators", "tokenize, ssplit, pos, lemma, ner, parse");
        properties.setProperty("ssplit.newlineIsSentenceBreak", "always");

        List<Document> documents = new ArrayList<Document>();
        String text = Utils.readTxtFile(new File("src/main/resources/Article.txt"));
//        String text = "So had I.\n I decided then that would never complain about getting older in CNN.\n This is a test sentence.";

        documents.add(new Document(text));

        this.stanfordCoreNLP = new StanfordCoreNLP(properties);

        for (Document document : documents) {
            try {
                processDocument(document);
                printSentences(document);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void processDocument(Document document) {
        Annotation annotation = new Annotation(document.getText());
        this.stanfordCoreNLP.annotate(annotation);

        processSentences(document, annotation);
    }

    private void processTokens(Sentence sentence, CoreMap sentencesStanford) {
        List<CoreLabel> coreLabels = sentencesStanford.get(CoreAnnotations.TokensAnnotation.class);
        int count = 1;

        for (CoreLabel cl : coreLabels) {
            String pos = cl.get(CoreAnnotations.PartOfSpeechAnnotation.class);
            String lemma = cl.lemma();
            Token token = new Token();

            token.setId(count++);
            token.setIdSentence(sentence.getId());
            token.setContent(cl.originalText());
            token.setStartPosition(cl.beginPosition());
            token.setEndPosition(cl.endPosition());
            token.setPosClass(pos);
            token.setStemming(lemma);

            if (cl.ner() != null && !cl.ner().equalsIgnoreCase("0")) {
                token.setNer(cl.ner());
            }

            String type = Utils.checkTokenType(token);

            if ("Symbol".equalsIgnoreCase(type) || this.stopList.contains(token.getContent().toLowerCase())) {
                token.setStopWord(true);
            }

            token.setType(type);

            if (!token.isStopWord()) {
                sentence.addTokenWithoutStopWords(token);
            }

            sentence.addToken(token);
        }
    }

    private void processSentences(Document document, Annotation annotation) {
        List<CoreMap> sentencesStanford = annotation.get(CoreAnnotations.SentencesAnnotation.class);
        List<Sentence> sentences = new ArrayList<Sentence>();

        int count = 1;

        for (CoreMap cm : sentencesStanford) {
            Sentence sentence = new Sentence(count, cm.toString());
            processTokens(sentence, cm);
            sentences.add(sentence);
        }

        document.setSentences(sentences);
    }

    private void printSentences(Document document) {
        for (Sentence sentence : document.getSentences()) {
            System.out.println("ID: " + sentence.getId() +
                                "\nSentence: " + sentence.getContent());

            printTokens(sentence);
        }
    }

    private void printTokens(Sentence sentence) {
        for (Token token : sentence.getTokens()) {
            long id = token.getId();
            String content = token.getContent();
            String ner = token.getNer();
            String pos = token.getPosClass();
            String stemming = token.getStemming();
            String type = token.getType();

            System.out.println("ID: " + id +
                                "\n Content: " + content +
                                "\n NER: " + ner +
                                "\n Stemming: " + stemming +
                                "\n Type: " + type +
                                "\n POS: " + pos);
        }

        System.out.println();
    }

}
