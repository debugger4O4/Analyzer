package service;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.datatransfer.StringSelection;
import java.awt.dnd.*;
import java.io.File;

/**
 * Класс перетаскивания файлов.
 */
public class DragTree extends JTree implements DragGestureListener,
        DragSourceListener {
    public DragTree() {
        DragSource dragSource = DragSource.getDefaultDragSource();

        dragSource.createDefaultDragGestureRecognizer(
                this,
                DnDConstants.ACTION_COPY_OR_MOVE,
                this
        );

        setModel(createTreeModel());

        addTreeExpansionListener(new TreeExpansionListener() {
            public void treeCollapsed(TreeExpansionEvent e) {
            }

            public void treeExpanded(TreeExpansionEvent e) {
                TreePath path = e.getPath();

                if (path != null) {
                    FileNode node = (FileNode)
                            path.getLastPathComponent();

                    if (node.isExplored()) {
                        DefaultTreeModel model =
                                (DefaultTreeModel) getModel();
                        node.explore();
                        model.nodeStructureChanged(node);
                    }
                }
            }
        });
    }

    public void dragGestureRecognized(DragGestureEvent e) {
        e.startDrag(
                DragSource.DefaultCopyDrop,
                new StringSelection(getFilename()),
                this
        );
    }

    public void dragDropEnd(DragSourceDropEvent e) {
    }

    public void dragEnter(DragSourceDragEvent e) {
    }

    public void dragExit(DragSourceEvent e) {
    }

    public void dragOver(DragSourceDragEvent e) {
    }

    public void dropActionChanged(DragSourceDragEvent e) {
    }

    public String getFilename() {
        TreePath path = getLeadSelectionPath();
        FileNode node = (FileNode) path.getLastPathComponent();
        return ((File) node.getUserObject()).getAbsolutePath();
    }

    private DefaultTreeModel createTreeModel() {
        File root = new File("for_analyze");
        FileNode rootNode = new FileNode(root);
        rootNode.explore();
        return new DefaultTreeModel(rootNode);
    }
}
