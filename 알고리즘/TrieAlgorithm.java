package algo;

import java.util.*;

public class TrieAlgorithm {
	
	// ��ó - https://the-dev.tistory.com/3
	
	private static class TrieNode {

		private Map<Character, TrieNode> childNodes = new HashMap<>();
		
		// ������ ���� ����
		private boolean isLastChar;
		
		Map<Character, TrieNode> getChildNodes() {
			return this.childNodes;
		}
		
		boolean isLastChar() {
			return this.isLastChar;
		}
		
		void setIsLastChar(boolean isLastChar) {
			this.isLastChar = isLastChar;
		}
		
		public String toString() {
			return childNodes.toString() + " " + isLastChar;
		}
	}

	private static class Trie {
		// ��Ʈ ���
		private TrieNode rootNode;
		
		Trie() {
			rootNode = new TrieNode();
		}
		
		boolean isRootEmpty() {
			return this.rootNode.getChildNodes().isEmpty();
		}
		
		// �ڽ� ��� �߰�
		void insert(String word) {
			TrieNode thisNode = this.rootNode;
			
			for(int i = 0, len = word.length(); i<len; i++) {
				// http://tech.javacafe.io/2018/12/03/HashMap/
				thisNode = thisNode.getChildNodes().computeIfAbsent(word.charAt(i), c->new TrieNode());
			}
			thisNode.setIsLastChar(true);
			
		}
		
		// Ư�� �ܾ ����ִ��� Ȯ��
		boolean contains(String word) {
			TrieNode thisNode = this.rootNode;
			
			for(int i = 0, len = word.length(); i<len; i++) {
				char character = word.charAt(i);
				TrieNode node = thisNode.getChildNodes().get(character);
				
				if(node == null) return false;
				
				thisNode = node;
			}
			
			return thisNode.isLastChar();
		}
		
		void delete(String word) {
			delete(this.rootNode, word, 0);
		}
		
		private void delete(TrieNode thisNode, String word, int index) {
			char character = word.charAt(index);
			
			// APPLE, PEN�� ���� �ƿ� ���� �ܾ��� ��� ���� ���
			if(!thisNode.getChildNodes().containsKey(character)) {
				System.out.println("There is no [" + word + "] in this Trie.");
				return;
			}
			
			TrieNode childNode = thisNode.getChildNodes().get(character);
			index++;
			
			if(index == word.length()) {

				// ���� ���������� insert�� �ܾ �ƴ� ���
				if(!childNode.isLastChar()) {
					System.out.println("There is no [" + word + "] in this Trie.");
					return;
				}
				
				childNode.setIsLastChar(false);
				
				// ���� ��� ����� ���� ������ �ڽ� ��尡 ������ ���� ����
				if(childNode.getChildNodes().isEmpty()) {
					thisNode.getChildNodes().remove(character);
				}
				
			} else {
				if(!thisNode.getChildNodes().isEmpty())
					delete(childNode, word, index);
				
				// ���� �� �ڽ� ��尡 ���� ���� ���� ������ �ٸ� �ܾ ���� ��� �ش� ��� ����
				if(!childNode.isLastChar()
						&& childNode.getChildNodes().isEmpty()) {
					thisNode.getChildNodes().remove(character);
				}
			}
		}
		
		
	}
	
	public static void main(String[] args) {
		
		Trie trie = new Trie();
		
		System.out.println(trie.isRootEmpty());
		
		trie.insert("SSAFY");
		trie.insert("COACH");
		trie.insert("COAT");
		trie.insert("SAMSUNG");
		
		System.out.println(trie.isRootEmpty());
		
		System.out.println("SSAFY: " + trie.contains("SSAFY"));
		System.out.println("COACH: " + trie.contains("COACH"));
		System.out.println("CO: " + trie.contains("CO"));
		System.out.println("SEPTEMBER: " + trie.contains("SEPTEMBER"));
		trie.delete("SS");
		trie.delete("COAT");
		
//		trie.delete("POP");
	}

}
