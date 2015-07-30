package sentimentanalysistablegeneration;

import java.util.ArrayList;

public class DocumentAnalysis {

    public static void main(String[] args) {
        String document = "Although echoing a number of design traits from the existing Galaxy S5, the Galaxy S6 is a handset with slimmer edges and more rounded corners. It also pairs these new metal edges with a Gorilla Glass rear.Lining up at just 6.8mm thick and 138g in weight, the S6 is undoubtedly a good looking phone. This could be down to how similar the device looks to the iPhone 6, however. With its soft curves and metal framing, it appears the S6 has taken more than a bit of 'inspiration' from Apple's leading handset.While a premium looking phone, there are still some trademark licks of Samsung on this phone. By which we mean at times it can look a bit cheap and tacky. The S6's colour schemes are garish at best. The phone's highly reflective rear cheapens its overall look and is prone to smudges and fingerprints. Other design points of note on the S6 include its protruding rear camera and enlarged physical home button which plans host to a newly improved fingerprint scanner. This second-generation sensor more closely resembles Apple's TouchID feature, with finger swipes replaced by simple presses. For those interested in more than surface looks, a Galaxy S6 teardown has offered up a look at how easy (read extremely bloody difficult) it is to prise apart the phone's seamless design and replace internal components. The Galaxy S6 teardown video (below) has revealed that although the phone's battery can (technically) be replaced, doing so is hardly worth the hassle.Samsung's screens are a traditional strong point, so expect the Galaxy S6's screen to be a stunner.";

        String[] splittedDocument = document.split("\\.");

        DependencyFiltering filter = new DependencyFiltering();
        ArrayList<String> filteredSentences = filter.dependencyFiltering(splittedDocument);

        SnlpPosOfWord snlpPos = new SnlpPosOfWord();
        PosTagMapper posTageMapper = new PosTagMapper();

        for (String sentence : filteredSentences) {

            ArrayList<ArrayList<WordPosTagsValuePair>> posListOfTweets = new ArrayList<>();

            ArrayList<Double> scores = new ArrayList<>();

            sentence = sentence.toLowerCase();
            sentence = sentence.replaceAll("([^a-zA-Z\\s])", "");

            System.out.println(sentence);

            ArrayList<WordPosTagsValuePair> posOfTweet = snlpPos.getPosOfTweet(sentence);
            posListOfTweets.add(posOfTweet);

            double totalScore = 0.0;

            for (WordPosTagsValuePair posOfTweet1 : posOfTweet) {
                String mapTag = posTageMapper.getMapTag(posOfTweet1.getPosTag());
                double sentiScore = -100.0;

                if (mapTag != null) {
                    try {
                        sentiScore = SentiWordNet.getInstance().extract(posOfTweet1.getWord(), mapTag);
                        totalScore += sentiScore;
                    } catch (Exception ex) {
                    }
                }
                System.out.println(posOfTweet1.getWord() + "\t" + posOfTweet1.getPosTag() + "\t" + mapTag + "\t" + sentiScore);
            }

            scores.add(totalScore);
            System.out.println("Score : " + totalScore + "\n\n");
        }

    }
}
