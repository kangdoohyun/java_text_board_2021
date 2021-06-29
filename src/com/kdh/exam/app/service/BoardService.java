package com.kdh.exam.app.service;

import com.kdh.exam.app.container.Container;
import com.kdh.exam.app.dto.Board;
import com.kdh.exam.app.repository.BoardRepository;

public class BoardService {
	BoardRepository boardRepository;

	public BoardService() {
		boardRepository = Container.getBoardRepository();
	}

	public Board getBoardById(int id) {
		return boardRepository.getBoardById(id);
	}

	public void makeTestData() {
		make("notice", "공지");
		make("free", "자유");
	}

	private int make(String code, String name) {
		return boardRepository.make(code, name);
	}

}
