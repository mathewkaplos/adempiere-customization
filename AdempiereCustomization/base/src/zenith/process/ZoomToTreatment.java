package zenith.process;

import org.compiere.apps.AEnv;
import org.compiere.apps.AWindow;
import org.compiere.apps.form.FormFrame;
import org.compiere.apps.form.VBOMDrop;
import org.compiere.model.MQuery;
import org.compiere.process.SvrProcess;

public class ZoomToTreatment extends SvrProcess
{
	
	@Override
	protected void prepare()
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected String doIt() throws Exception
	{
		//openPurchaseOrders();
		openBOMDrop() ;
		return null;
	}
	private boolean openPurchaseOrders(){
		  final int Treatment_WINDOW_ID = 1000004;
		  /** filter the data - needs to be generated for real use... */
		  String whereString = " hms_treatment_doc_ID=1008348"; 
		  final AWindow poFrame = new AWindow(); 
		  final MQuery query = new MQuery("hms_treatment_doc"); 
		  query.addRestriction(whereString); 
		  final boolean ok = poFrame.initWindow(Treatment_WINDOW_ID, query);  
		  if (!ok) { 
		     return false; 
		  } 
		  poFrame.pack(); 
		  AEnv.showCenterScreen(poFrame);
		  return true; 
		}
	public boolean openBOMDrop() {
	       FormFrame frame = new FormFrame();
	       int BOM_DROP_FORM_ID = 114;
	       boolean ok = frame.openForm(BOM_DROP_FORM_ID);
	       if (!ok)
	            return false;
	       VBOMDrop panel = (VBOMDrop) frame.getFormPanel(); // if you need to do something with the panel
	       AEnv.addToWindowManager(frame);
	       AEnv.positionCenterScreen(frame);
	       frame.setVisible(true);
	       return true;
	 }
}
