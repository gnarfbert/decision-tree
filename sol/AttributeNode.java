package sol;

import java.util.List;

import src.ITreeNode;
import src.Row;

/**
 * A class representing an attribute in the decision tree, these are nodes with outgoing ValueEdges
 */
public class AttributeNode implements ITreeNode{
    private List<ValueEdge> outgoingEdges;
    private String defaultChoice;

    private String attribute;


    /**
     * Constructor for AttributeNode object
     * @param atr is a string that represents a categorical attribute from the inputted dataset
     * @param decision is a string that represents a default decision when
     * @param edges is a list of ValueEdge objects that represents the outgoing value pointers
     */
    public AttributeNode(String atr, String decision, List<ValueEdge> edges) {
        this.attribute = atr;
        this.defaultChoice = decision;
        this.outgoingEdges = edges;
    }

    /**
     * Recursively checks to see if the current object is a decision leaf
     * @param forDatum the datum to look up a decision for
     * @return
     */
    @Override
    public String getDecision(Row forDatum) {
        String target = forDatum.getAttributeValue(this.attribute);
        for(ValueEdge edge : this.outgoingEdges) {
            if(target.equals(edge.getValue())) {
                return edge.getChild().getDecision(forDatum);
            }
        }
        return this.defaultChoice;
    }
}
