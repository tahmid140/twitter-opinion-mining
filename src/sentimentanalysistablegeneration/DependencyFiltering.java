package sentimentanalysistablegeneration;

import edu.stanford.nlp.ling.Sentence;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.trees.GrammaticalStructure;
import edu.stanford.nlp.trees.GrammaticalStructureFactory;
import edu.stanford.nlp.trees.PennTreebankLanguagePack;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import edu.stanford.nlp.trees.TypedDependency;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class DependencyFiltering {

    private static final String[] ALL_KEYWORDS = {"battery", "camera", "screen", "sound",
        "touch", "display", "phone", "galaxy", "s6"};

    private static LexicalizedParser lp = LexicalizedParser.loadModel(
            "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz",
            "-maxLength", "80", "-retainTmpSubcategories");

    public ArrayList<String> dependencyFiltering(String[] sentences) {
        ArrayList<String> result = new ArrayList<>();

        Set<String> keywordsSet = new HashSet<>(Arrays.asList(ALL_KEYWORDS));

        for (String sentence : sentences) {

            TreebankLanguagePack tlp = new PennTreebankLanguagePack();
            GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();

            boolean isNeedToDoPosTagMapping = false;
            for (String feature : ALL_KEYWORDS) {
                if (sentence.contains(feature)) {
                    isNeedToDoPosTagMapping = true;
                }
            }

            if (!isNeedToDoPosTagMapping) {
                continue;
            }

            boolean isNsubj = false;
            boolean isAmod = false;
            boolean isDobj = false;

            String text = sentence.replaceAll("([^a-zA-Z\\s])", "");
            String[] sent = text.split(" ");

            Tree parse = lp.apply(Sentence.toWordList(sent));
            GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
            Collection<TypedDependency> tdl = gs.typedDependenciesCCprocessed();

            Object[] list = tdl.toArray();

            TypedDependency typedDependency;
            for (Object object : list) {
                typedDependency = (TypedDependency) object;
                if (typedDependency.reln().getShortName().equals("nsubj")) {
                    if (keywordsSet.contains(typedDependency.dep().toString().split("-")[0])
                            || keywordsSet.contains(typedDependency.gov().toString().split("-")[0])) {
                        isNsubj = true;
                    }
                } else if (typedDependency.reln().getShortName().equals("dobj")) {
                    if (keywordsSet.contains(typedDependency.dep().toString().split("-")[0])
                            || keywordsSet.contains(typedDependency.gov().toString().split("-")[0])) {
                        isDobj = true;
                    }
                } else if (typedDependency.reln().getShortName().equals("amod")) {
                    if (keywordsSet.contains(typedDependency.dep().toString().split("-")[0])
                            || keywordsSet.contains(typedDependency.gov().toString().split("-")[0])) {
                        isAmod = true;
                    }
                }
            }

            if (isNsubj || isDobj || isAmod) {
                result.add(text);
            }else{
                //result.add(text);
            }
        }
        return result;
    }
}
