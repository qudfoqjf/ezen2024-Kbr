package ezenweb.model.dao;

import ezenweb.model.dto.BoardDto;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class BoardDao extends Dao {
    //1. 글쓰기 처리[글쓰기를 성공했을때 자동 생성된 글번호 반환, 실패시 0]
    public long doPostBoardWrite(BoardDto boardDto){
        System.out.println("BoardDao.doPostBoardWrite");
        System.out.println("boardDto = " + boardDto);

        try {
            String sql="insert into board(btitle, bcontent,bfile,mno,bcno) value(?,?,?,?,?)";
            ps=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,boardDto.getBtitle());
            ps.setString(2,boardDto.getBcontent());
            ps.setString(3,boardDto.getBfile());
            ps.setLong(4,boardDto.getMno());
            ps.setLong(5,boardDto.getBcno());
            int count= ps.executeUpdate();
            if(count==1){
                rs=ps.getGeneratedKeys();
                if(rs.next()){return rs.getLong(1);}
            }

        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return 0;
    }
    //2-2 전체 게시물 수 호출
    public int getBoardSize(int bcno, String field,String value){
        System.out.println("bcno = " + bcno + ", field = " + field + ", value = " + value);
        try {
            String sql="select count(*) from board b inner join member m on b.mno ";
            //================1. 만약에 카테고리 조건이 있으면 where 추가.
            if(bcno>0){sql += " where b.bcno = "+ bcno;}
            //================2. 만약에 검색 있을때
            if(!value.isEmpty()){   System.out.println("검색 키워드가 존재");
                if(bcno>0){sql+=" and ";}   // 카테고리가 있을 때, and 로 연결
                else{sql += " where ";}       // 카테고리가 없을 때, where 로 연결
                sql+= field+" like '%"+value+"%' ";
            }

            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            if(rs.next()){return rs.getInt(1);}
        }catch(Exception e){
            System.out.println("e = " + e);
        }return 0;
    }
    //2. 전체 글 출력 호출
    public List<BoardDto> dogetBoardViewList(int startrow,int pageBoardSize, int bcno, String field,String value){
        System.out.println("BoardController.dogetBoardViewList");
        BoardDto boardDto=null;
        List<BoardDto> list=new ArrayList<>();
        try {
            // SQL 앞부분
            String sql="select * from board b inner join member m " +
                    " on b.mno= m.no ";
            // SQL 가운데 부분
            if(bcno>0){sql+=" where bcno ="+bcno;}

            //================2. 만약에 검색 있을때
            if(!value.isEmpty()){   System.out.println("검색 키워드가 존재");
                if(bcno>0){sql+=" and ";}   // 카테고리가 있을 때, and 로 연결
                else{sql += " where ";}       // 카테고리가 없을 때, where 로 연결
                sql+= field+" like '%"+value+"%' ";
            }

            // SQL 뒷부분
            sql+=" order by b.bdate desc " +
                    " limit ?, ?";

            ps=conn.prepareStatement(sql);
            ps.setInt(1,startrow);
            ps.setInt(2,pageBoardSize);
            rs=ps.executeQuery();
            while(rs.next()){
                boardDto= new BoardDto(rs.getLong("bno"),
                        rs.getString("btitle"),rs.getString("bcontent"),
                        rs.getString("bfile"), rs.getLong("bview"), rs.getString("bdate"),
                        rs.getLong("mno"),rs.getLong("bcno"),null,
                        rs.getString("id"),rs.getString("img")
                );
                list.add(boardDto);
            }


        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return list;
    }

    //3. 개별 글 출력 호출

    public BoardDto doGetBoardView(int bno){System.out.println("BoardDao.doGetBoardView");
        BoardDto boardDto=null;
        try {
            String sql="select * from board b inner join member m on b.mno=m.no where b.bno =?";
            ps=conn.prepareStatement(sql);
            ps.setLong(1,bno);rs=ps.executeQuery();
            if(rs.next()){
                boardDto= new BoardDto(rs.getLong("bno"),
                        rs.getString("btitle"),rs.getString("bcontent"),
                        rs.getString("bfile"), rs.getLong("bview"), rs.getString("bdate"),
                        rs.getLong("mno"),rs.getLong("bcno"),null,
                        rs.getString("id"),rs.getString("img"));
            }

        }catch (Exception e){
            System.out.println("e = " + e);
        }

        return boardDto;
    }
    // 3-2. 조회수
    public void doViewUpdate(int bno){
        try{
            String sql="update board set bview= bview + 1 where bno = "+ bno;
            ps=conn.prepareStatement(sql);
            ps.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    //4. 글 수정 처리
    public Boolean doPutBoard(BoardDto boardDto){
        System.out.println("BoardController.doPutBoard");
        try{
            String sql= "update board set btitle=?, bcontent=?, bcno=? where bno=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,boardDto.getBtitle());
            ps.setString(2,boardDto.getBcontent());
            ps.setLong(3,boardDto.getBcno());
            ps.setLong(4,boardDto.getBno());
            int count =ps.executeUpdate();
            if(count==1){
                return true;
            }
        }catch (Exception e){
            System.out.println("e = " + e);
        }

        return false;
    }

    //5. 글 삭제 처리
    public boolean doDeleteBoard( int bno){
        System.out.println("BoardDao.doDeleteBoard");
        try {
            String sql="delete from board where bno = "+ bno;
            ps=conn.prepareStatement(sql);
            int count= ps.executeUpdate();
            if(count==1){
                return true;
            }
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return false;
    }
    //6. 다운로드 처리(함수만들때 고민할점 1.매개변수: 파일명  2. 반환 3. 사용처:get http 요청)
    public void getBoardFileDownload(String bfile){
        System.out.println("BoardController.getBoardFileDownload");
        System.out.println("bfile = " + bfile);
        return;
    }

}
