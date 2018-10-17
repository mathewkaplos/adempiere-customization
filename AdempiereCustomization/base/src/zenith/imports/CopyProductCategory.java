package zenith.imports;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.compiere.model.MProductCategory;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

public class CopyProductCategory extends SvrProcess
{
	
	@Override
	protected void prepare()
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected String doIt() throws Exception
	{
		copyProductCategories();
		return null;
	}

	private void copyProductCategories()
	{
		String sql = "SELECT category_id ,category_name FROM z_category";
		PreparedStatement ps = null;
		try
		{
			ps = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				int category_id = rs.getInt(1);
				String category_name = rs.getString(2).toUpperCase();

				MProductCategory productCategory = new MProductCategory(getCtx(), 0, getName());
				productCategory.setValue(category_name);
				productCategory.setName(category_name);

				productCategory.setImport_category_id(category_id);

				productCategory.saveEx(get_TrxName());

			}
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			DB.close(ps);
			ps = null;
		}
	}

}
