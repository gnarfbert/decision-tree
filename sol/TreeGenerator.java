package sol;

import com.sun.source.tree.Tree;
import src.ITreeGenerator;
import src.ITreeNode;
import src.Row;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that implements the ITreeGenerator interface used to generate a decision tree
 */
public class TreeGenerator implements ITreeGenerator<Dataset> {
    private ITreeNode root;


    /**
     * Constructor for a TreeGenerator
     */
    public TreeGenerator() {

    }

    @Override
    public void generateTree(Dataset trainingData, String targetAttribute) {
        this.root = this.generateTreeHelper(trainingData, targetAttribute);
    }

    @Override
    public String getDecision(Row datum) {
        return this.root.getDecision(datum);
    }

    /**
     * Recursive helper function that generates the tree. The base case
     * checks if we should make a decision leaf and the recursive case
     * creates a AttributeNode
     * @param trainingData
     * @param targetAttribute
     * @return a ITreeNode object
     */
    private ITreeNode generateTreeHelper(Dataset trainingData, String targetAttribute) {
        if(this.makeLeaf(trainingData, targetAttribute)){
            String decisionValue = trainingData
                    .getDataObjects().get(0).getAttributeValue(targetAttribute);
            return new DecisionLeaf(decisionValue);
        }
        else {
            trainingData.getAttributeList().remove(targetAttribute);
            String splitAttribute = trainingData.getAttributeToSplitOn();
            String defaultValue = trainingData.getMostCommon(targetAttribute);
            List<ValueEdge> edges = new ArrayList<>();
            List<Dataset> smallerSet = trainingData.split(splitAttribute);
            for(Dataset set : smallerSet) {
                Row singleObject = set.getDataObjects().get(0);
                String value = singleObject.getAttributeValue(splitAttribute);
                ITreeNode child = this.generateTreeHelper(set,targetAttribute);
                ValueEdge singleEdge = new ValueEdge(value, child);
                edges.add(singleEdge);
            }
            return new AttributeNode(splitAttribute,defaultValue,edges);
        }
    }


    /**
     * Takes in a Dataset and confirms the conditions to make a decision leaf
     * @param set a Dataset object
     * @param targetAttribute a string that represents the attribute of interest
     * @return a boolean that indicates whether to make a decision leaf
     */
    private boolean makeLeaf(Dataset set, String targetAttribute) {
        if (set.getAttributeList().size() != 0) {
            String targetValue = set.getDataObjects().get(0).getAttributeValue(targetAttribute);
            for (Row object : set.getDataObjects()) {
                if (!object.getAttributeValue(targetAttribute).equals(targetValue)) {
                    return false;
                }
            }
        }
        return true;
    }

}
