package sol;

import src.ITreeNode;
import src.Row;

/**
 * A class representing a leaf in the decision tree, these are nodes without any outgoing ValueEdge pointers.
 */
public class DecisionLeaf implements ITreeNode {
    private String target;


    /**
     * Constructor for a DecisionLeaf
     * @param value is a string that represents a value
     */
    public DecisionLeaf(String value) {
        this.target = value;
    }

    /**
     * Recursively traverses decision tree to return tree's decision for a row.
     * @param forDatum the datum to lookup a decision for
     * @return the decision tree's decision
     */
    @Override
    public String getDecision(Row forDatum) {
        return this.target;
    }
}
