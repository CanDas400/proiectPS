package BusinessLogic;

public class ExportFactory {

	
	public Exporter getExporter(String exporterType){
		if(exporterType == null){
			return null;
		}
		else if(exporterType.equalsIgnoreCase("Print")){
			return new PrintExporter();
		}
		else if(exporterType.equalsIgnoreCase("Csv")){
			return new CsvExporter();
		}
		return null;
	}
}
