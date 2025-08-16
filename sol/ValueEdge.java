package sol;

import src.ITreeNode;

/**
 * A class that represents the edge of an attribute node in the decision tree
 */
public class ValueEdge {
    private ITreeNode child;

    private String value;

    /**
     * Constructor for a ValueEdge
     * @param value
     * @param node
     */
    public ValueEdge(String value, ITreeNode node) {
        this.value = value;
        this.child = node;
    }

    /**
     * A getter method for the value
     * @return a String that represents the value
     */
    public String getValue() {
        return this.value;
    }

    /**
     * A getter method for the child of the edge
     * @return a ITreeNode object that represents the child
     */
    public ITreeNode getChild() {
        return this.child;
    }

}
