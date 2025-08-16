package sol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import src.AttributeSelection;
import src.IDataset;
import src.Row;


/**
 * A class representing a training dataset for the decision tree
 */
public class Dataset implements IDataset {

    private AttributeSelection selectionType;
    private List<String> attributeList;
    private List<Row> dataObjects;

    /**
     * Constructor for a Dataset object
     * @param attributeList - a list of attributes
     * @param dataObjects -  a list of rows
     * @param selectionType - an enum for which way to select attributes
     */
    public Dataset(List<String> attributeList, List<Row> dataObjects, AttributeSelection selectionType) {
        this.selectionType = selectionType;
        this.attributeList = attributeList;
        this.dataObjects = dataObjects;

    }


    /**
     * Identifies the selection type and splits the dataset on an attribute
     * @return A String that represents the attribute to split on
     */
    public String getAttributeToSplitOn() {
        if (this.selectionType == AttributeSelection.ASCENDING_ALPHABETICAL) {
            return this.attributeList.stream().sorted().toList().get(0);
        }
        if (this.selectionType == AttributeSelection.DESCENDING_ALPHABETICAL) {
            return this.attributeList.stream().sorted().toList().get(this.attributeList.size() - 1);
        }
        if (this.selectionType == AttributeSelection.RANDOM) {
            Random random = new Random();

            int upperBound = this.attributeList.size();
            int randomNum = random.nextInt(upperBound);

            return this.attributeList.get(randomNum);
        }

        throw new RuntimeException("Non-Exhaustive Selection Type");
    }

    /**
     * Splits the larger Dataset into a list of partitioned datasets that does not
     * contain the attribute to be split on and the target attribute
     * @param attributeSplit
     * @return a list of datasets
     */
    public List<Dataset> split(String attributeSplit) {
        List<Dataset> result = new ArrayList<>();
        HashMap<String, List<Row>> collection = new HashMap<>();
        List<String> copyAttribute = new ArrayList<>();
        String splitOn = attributeSplit;

        //Copies every attribute except the one to be split on
        for(String attribute : this.attributeList) {
            if(!attribute.equals(attributeSplit)) {
                copyAttribute.add(attribute);
            }
        }


        //Splits the row objects list into smaller lists that correlate with the key.
        for(Row object :this.dataObjects) {
            String value = object.getAttributeValue(splitOn);
            if(collection.containsKey(value)) {
                collection.get(value).add(object);
            }
            else {
                List<Row> copyObjects = new ArrayList<>();
                copyObjects.add(object);
                collection.put(value,copyObjects);
            }
        }
        //Creates a list of partitioned datasets
        for(List<Row> r : collection.values()) {
            result.add(new Dataset(copyAttribute, r, this.selectionType));
        }
        return result;
    }

    /**
     * Loops through the Dataset's objects, counting the most common value
     * @param targetAttribute
     * @return A String that represents the most common value
     */
    public String getMostCommon(String targetAttribute) {
        HashMap<String, Integer> counter = new HashMap<>();
        String common = "";
        int max = 0;
        for(Row object : this.dataObjects) {
            String targetValue = object.getAttributeValue(targetAttribute);
            int count = counter.getOrDefault(targetValue, 0);
            counter.put(targetValue, count + 1);
            if(count > max) {
                max = count;
                common = targetValue;
            }
        }

        return common;
    }


    @Override
    public List<String> getAttributeList() {
        return this.attributeList;
    }

    @Override
    public List<Row> getDataObjects() {
        return this.dataObjects;
    }

    @Override
    public AttributeSelection getSelectionType() {
        return this.selectionType;
    }

    @Override
    public int size() {
        return this.dataObjects.size();
    }
}