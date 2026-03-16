# Java Todo App

JavaとMySQLを使ったシンプルなTodoアプリです。

## 概要
コンソール上でタスクの管理ができます。

## 機能
- タスク一覧表示
- 未完了タスク一覧表示
- タスク追加
- タスク完了
- タスク検索
- タスク削除

## 使用技術
- Java
- MySQL
- JDBC

## データーベース
CREATE DATABASE taskapp;
USE taskapp;
CREATE TABLE tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(30),
    completed BOOLEAN
);

## 実行方法
1. MySQLを起動
2. データーベースを作成
3. Javaプログラムを実行