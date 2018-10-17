package zz.form;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class JTableFiltering extends JFrame
{
   private JTable table;
   private TableRowSorter< TableModel > sorter;
   private BooleanModel tblModel;
   
   private JComboBox combo;
   private JCheckBox checkBox;
   
   private class BooleanModel extends AbstractTableModel
   {
      public Boolean[] model;
      
      public BooleanModel()
      {
         this.model = new Boolean[] { true, true, true, true, true };
      }
      
      @Override
      public int getColumnCount()
      {
         return 2;
      }
      
      @Override
      public int getRowCount()
      {
         return model.length;
      }
      
      @Override
      public Object getValueAt( int row, int column )
      {
         switch( column ) {
            case 0:
               return row;
            case 1:
               return model[ row ];
         }
         return null;
      }
      
      @Override
      public boolean isCellEditable( int rowIndex, int columnIndex )
      {
         return false;
      }
   }
   
   public JTableFiltering()
   {
      super( "Why won't you filter?" );
      
      JPanel contentPane = new JPanel( new BorderLayout() );
      setContentPane( contentPane );
 
      tblModel = new BooleanModel();
      table = new JTable( tblModel );
      contentPane.add( table, BorderLayout.CENTER );
      
      sorter = new TableRowSorter< TableModel >( tblModel );
      RowFilter< TableModel, Integer > filter = new RowFilter< TableModel, Integer >() {
         @Override
         public boolean include( javax.swing.RowFilter.Entry< ? extends TableModel, ? extends Integer > entry )
         {
            TableModel model = entry.getModel();
            return (Boolean)model.getValueAt( entry.getIdentifier(), 1 );
         }
      };
      sorter.setRowFilter( filter );
      
      //I thought this would resolve my problem....
      sorter.setSortsOnUpdates( true );
      
      table.setRowSorter( sorter );
      
      JPanel controls = new JPanel();
      contentPane.add( controls, BorderLayout.EAST );
      
      combo = new JComboBox( new DefaultComboBoxModel( new Object[] { 0, 1, 2, 3, 4 } ) );
      controls.add( combo );
      
      checkBox = new JCheckBox( "Set", true );
      controls.add( checkBox );
      
      JButton apply = new JButton( "Apply Change" );
      controls.add( apply );
      
      apply.addActionListener( new ActionListener() {
         @Override
         public void actionPerformed( ActionEvent e )
         {
            Integer i = (Integer)combo.getSelectedItem();
            boolean selected = checkBox.isSelected();
            tblModel.model[ i ] = selected;
            
            tblModel.fireTableRowsUpdated( i, i );
         }
      });
      
      JButton applySorter = new JButton( "Reapply Sorter" );
      controls.add( applySorter );
      
      applySorter.addActionListener( new ActionListener() {
         @Override
         public void actionPerformed( ActionEvent e )
         {
            table.setRowSorter( sorter );
         }
      });
      
      setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      setResizable( false );
      
      pack();
      setVisible( true );
   }
   
   public static void main( String[] args )
   {
      new JTableFiltering();
   }
}