package zenith.model;

import java.sql.ResultSet;
import java.util.Properties;

public class DoctorNotes extends X_hms_doc_notes
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9178706911738345674L;

	public DoctorNotes(Properties ctx, int hms_doc_notes_ID, String trxName)
	{
		super(ctx, hms_doc_notes_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public DoctorNotes(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
