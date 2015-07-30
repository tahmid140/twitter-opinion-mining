package sentimentanalysistablegeneration;

public class PosTagMapper {

    public String getMapTag(String tag) {

        /*
         if treebank_tag.startswith('J'):
         return 'a'
         elif treebank_tag.startswith('V'):
         return 'v'
         elif treebank_tag.startswith('N'):
         return 'n'
         elif treebank_tag.startswith('R'):
         return 'r'
         else:
         return ''
         */
        switch (tag.charAt(0)) {
            case 'J':
                return "a";
            case 'V':
                return "v";
            case 'N':
                return "n";
            case 'R':
                return "r";
            default:
                return null;
        }
    }
}
