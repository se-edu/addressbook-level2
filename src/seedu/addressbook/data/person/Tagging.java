package seedu.addressbook.data.person;

import java.util.ArrayList;

public class Tagging {
    
    class Pair {
        private String addRemove;
        private String tagName;
        
        public Pair(String addRemove, String tagName) {
            this.addRemove = addRemove;
            this.tagName = tagName;
        }
        
        public String getAddRemoveTag() {
            return this.addRemove;
        }
        
        public String getTagName() {
            return this.tagName;
        }
    }
    
    private Person person;
    private ArrayList<Pair> taggings = new ArrayList<Pair>();
    
    public Tagging(Person person) {
        this.person = person;
    }
    
    /*
     * add / remove a tag from a person
     * need to specify what tag (add / remove) it is
     */
    public void addRemoveTag(String addRemove, String tag) {
        taggings.add(new Pair(addRemove, tag));
    }
    
    public String getTag() {
        int size = taggings.size();
        return taggings.get(size-1).getTagName();
    }
    
    public String getAddRemove() {
        int size = taggings.size();
        return taggings.get(size-1).getAddRemoveTag();
    }
    
    @Override
    public String toString() {
        StringBuilder format = new StringBuilder();
        for (int x = 0; x <= taggings.size(); x++) {
            format.append(taggings.get(x).getAddRemoveTag())
                  .append(" ")
                  .append(person)
                  .append(" [")
                  .append(taggings.get(x).getTagName())
                  .append("]\n");
        }
        return format.toString();
    }
    
}
