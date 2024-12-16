package service;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Класс графического интерфейся приложения.
 */
public class AnalyzerFrame extends JFrame implements DropTargetListener {

    private final JTextPane textPane = new JTextPane();

    public AnalyzerFrame() {
        super("Анализатор");
        new DropTarget(textPane,
                DnDConstants.ACTION_COPY_OR_MOVE,
                this);
        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                createTreePanel(),
                createTextPanel()
        );
        splitPane.setDividerLocation(250);
        splitPane.setOneTouchExpandable(true);
        getContentPane().add(splitPane, BorderLayout.CENTER);
    }

    private JPanel createTextPanel() {
        JPanel textPanel = new JPanel();
        textPane.setText("Поместите сюда файлы для анализа.");
        textPane.setEnabled(false);
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textPane, BorderLayout.CENTER);
        return textPanel;
    }

    private JPanel createTreePanel() {
        JPanel treePanel = new JPanel();
        DragTree tree = new DragTree();

        treePanel.setLayout(new BorderLayout());
        treePanel.add(new JScrollPane(tree), BorderLayout.CENTER);
        treePanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Выберите и перенесите файлы для анализа."));

        return treePanel;
    }

    private void readFile(final String filename) {
        int i;
        try (FileInputStream fileInputStream = new FileInputStream(filename)) {
            while ((i = fileInputStream.read()) != -1) {
                System.out.print((char) i);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void drop(DropTargetDropEvent e) {
        try {
            DataFlavor stringFlavor = DataFlavor.stringFlavor;
            Transferable tr = e.getTransferable();

            if (e.isDataFlavorSupported(stringFlavor)) {
                String filename =
                        (String) tr.getTransferData(stringFlavor);

                e.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                readFile(filename);
                e.dropComplete(true);
            } else {
                e.rejectDrop();
            }
        } catch (IOException | UnsupportedFlavorException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public void dragEnter(DropTargetDragEvent e) {
    }

    @Override
    public void dragExit(DropTargetEvent e) {
    }

    @Override
    public void dragOver(DropTargetDragEvent e) {
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent e) {
    }
}
