# decision tree
this is a Java program that takes a dataset from a CSV file, trains a decision tree on the provided data,
and evaluates the tree to predict categorical attributes based on the user.

## installation
- setup Java's Software Development Kit, install any version of Java Development Kit (JDK) newer than version 17.
- add the necessary dependencies from the lib folder into your IDE.

## usage
- import your training dataset and testing dataset of interest into the data folder.
- in the src folder run the main method in the DecisionTreeTester class.

## classes

AttributeNode: A Class which represents a Node in a decision tree. This Class implements the ITreeNode Interface.

BasicDatasetTest: A Class with a few simple tests on your decision tree implementation.

Dataset: A Class which represents a training data set. This can be thought of as a table, where the column values are
attributes and each row represents an item in the data (rows will be represented by the Row class). This Class
implements the IDataclass Interface.

DecisionLeaf: A Class which represents a Leaf in a decision tree. This Class implements the ITreeNode interface.

DecisionTreeTest: This is where you will write your tests!

TreeGenerator: This Class will be responsible for creating the Decision Tree given a Dataset. This Class implements
the ITreeGenerator Interface.

ValueEdge: A Class which represents an edge in the decision tree.

DecisionTreeCSVParser: This Class provides a static method which takes in a CSV of data and outputs a List of rows. You
should not edit this Class.

DecisionTreeTester: This Class provides methods for testing the accuracy of your Decision Tree on testing data.
We have marked some lines that you can edit to change which dataset this runs on. Otherwise, you should not edit
anything else in this Class.

IDataset: This Interface contains methods for getting information about a dataset. You should not edit this Interface.

ITreeGenerator: This Interface contains methods for generating a decision tree and getting the decision for a Row.
You should not edit this Interface.

ITreeNode: This Interface contains a method to get a decision given a Row. You should not edit this Interface.

Row: A Class which represents a row in a dataset. You should not edit this Class.
