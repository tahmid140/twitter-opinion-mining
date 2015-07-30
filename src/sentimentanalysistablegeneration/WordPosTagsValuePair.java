package sentimentanalysistablegeneration;

public class WordPosTagsValuePair {
    private String word;
    private String posTag;

    public WordPosTagsValuePair(String word, String posTag) {
        this.word = word;
        this.posTag = posTag;
    }
    
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPosTag() {
        return posTag;
    }

    public void setPosTag(String posTag) {
        this.posTag = posTag;
    }
}
