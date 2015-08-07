package com.plter.lrceditor.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by plter on 7/27/15.
 */
public class LRCEditorController implements Initializable{
    public Label label;
    public Button btnInsertTimeTag;
    public TextArea taTextContent;

    private String timeTag;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTimeTag("[00:00]");
    }

    public void setTimeTag(String timeTag) {
        this.timeTag = timeTag;
        btnInsertTimeTag.setText(String.format("插入时间%s", timeTag));
    }

    public String getTimeTag() {
        return timeTag;
    }

    public void btnInsertTimeTagActionHandler(ActionEvent actionEvent) {
        int selectStartIndex = taTextContent.getSelection().getStart();
        int currentLineStartIndex = taTextContent.getText().lastIndexOf("\n",selectStartIndex)+1;

        taTextContent.insertText(currentLineStartIndex, getTimeTag());

        int nextLineStartIndex = taTextContent.getText().indexOf("\n",currentLineStartIndex);
        if (nextLineStartIndex>-1){
            nextLineStartIndex = nextLineStartIndex+1;

            int nextLineEndIndex = taTextContent.getText().indexOf("\n",nextLineStartIndex);
            taTextContent.selectRange(nextLineStartIndex,nextLineEndIndex>-1?nextLineEndIndex:taTextContent.getLength());
        }
    }
}
