package com.mumu.i18n.editor.service.impl;

import com.mumu.i18n.editor.service.I18NFileReaderService;
import com.mumu.i18n.editor.vo.content.Comment;
import com.mumu.i18n.editor.vo.content.IEntry;
import com.mumu.i18n.editor.vo.content.Pair;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class I18NFileReaderServiceImpl implements I18NFileReaderService {
    @Override
    public List<IEntry> readI18NFile(String filePath, boolean unicodeToStr) {
        List<IEntry> entryList = new ArrayList<>();
        File file = new File(filePath);
        if(!file.exists()){
            return entryList;
        }
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), Charset.forName("UTF-8")))){
            String line = null;

            while((line = br.readLine()) != null){
                if(StringUtils.isBlank(line)){
                    entryList.add(new Comment(line));
                }else if(line.trim().startsWith("#")){
                    entryList.add(new Comment(line));
                }else{
                    String[] kv = StringUtils.split(line, "=", 2);
                    String value = unicodeToStr?unicodeToChina(kv[1]):kv[1];
                    entryList.add(new Pair(kv[0], value));
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return entryList;
    }

    public static String unicodeToChina(String str) {
        Charset set = Charset.forName("UTF-16");
        Pattern p = Pattern.compile("\\\\u([0-9a-fA-F]{4})");
        Matcher m = p.matcher( str );
        int start = 0 ;
        int start2 = 0 ;
        StringBuffer sb = new StringBuffer();
        while( m.find( start ) ) {
            start2 = m.start() ;
            if( start2 > start ){
                String seg = str.substring(start, start2) ;
                sb.append( seg );
            }
            String code = m.group( 1 );
            int i = Integer.valueOf( code , 16 );
            byte[] bb = new byte[ 4 ] ;
            bb[ 0 ] = (byte) ((i >> 8) & 0xFF );
            bb[ 1 ] = (byte) ( i & 0xFF ) ;
            ByteBuffer b = ByteBuffer.wrap(bb);
            sb.append( String.valueOf( set.decode(b) ).trim() );
            start = m.end() ;
        }
        start2 = str.length() ;
        if( start2 > start ){
            String seg = str.substring(start, start2) ;
            sb.append( seg );
        }
        return sb.toString() ;
    }
}
