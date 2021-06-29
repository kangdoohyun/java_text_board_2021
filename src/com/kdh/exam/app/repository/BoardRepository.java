package com.kdh.exam.app.repository;

import java.util.ArrayList;
import java.util.List;

import com.kdh.exam.app.dto.Board;
import com.kdh.exam.util.Util;

public class BoardRepository {
	List<Board> boards = new ArrayList<>();
	int lastId = 0;
	
	public Board getBoardById(int id) {
		for(Board board : boards) {
			if(board.getId() == id) {
				return board;
			}
		}
		return null;
	}

	public int make(String code, String name) {
		int id = ++lastId;
		
		boards.add(new Board(id, Util.getNowDateStr(), Util.getNowDateStr(), code, name));
		
		lastId = id;
		
		return id;
	}

}
