package saltsheep.dialog.file;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class FormatHelper {
	
	@Nullable
	public static MethodFormat formatMethod(String raw) {
		if(raw.endsWith(")")&&raw.matches(".*[(].*[)]")) {
			String[] fresh = raw.substring(0, raw.length()-1).split("[(]",2);
			String mainWord = fresh[0].replaceAll(" ", "");
			char[] temp = fresh[1].toCharArray();
			int leftBraceCount = 0;
			int rightBraceCount = 0;
			for(int i=0;i<temp.length;i++) {
				if(temp[i]=='('||temp[i]=='['||temp[i]=='{')
					leftBraceCount++;
				else if(temp[i]==')'||temp[i]==']'||temp[i]=='}')
					rightBraceCount++;
				else if(leftBraceCount==rightBraceCount&&temp[i]==';'&&!(i>0&&temp[i-1]=='\\'))
					temp[i] = '玨';
			}
			String[] paramsRaw = new String(temp).split("玨");
			Map<String,String> params = new HashMap<String, String>();
			for(String paramRaw:paramsRaw) {
				String[] param = paramRaw.split("=",2);
				if(param.length!=2)
					continue;
				params.put(param[0].replaceAll(" ", ""), param[1]);
			}
			return new MethodFormat(mainWord, params);
		}
		return null;
	}

	public static class MethodFormat{
		public String methodWord;
		public Map<String,String> params;
		private MethodFormat(String methodWord,Map<String,String> params) {
			this.methodWord = methodWord;
			this.params = params;
		}
	}
	
}
