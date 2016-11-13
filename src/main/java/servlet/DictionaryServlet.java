package servlet;

import com.mysql.jdbc.Driver;
import model.Dictionary;
import util.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/dictionary")
public class DictionaryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("add")) {
            add(req, resp);
        }
        if (action.equals("query")) {
            query(req, resp);
        }
        if (action.equals("search")) {
            search(req, resp);
        }
        if (action.equals("update")) {
            update(req, resp);
        }
        if (action.equals("remove")) {
            remove(req, resp);
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String chinese = request.getParameter("chinese").trim();
        String english = request.getParameter("english").trim();
        String phonetic = request.getParameter("phonetic").trim();
        String partOfSpeech = request.getParameter("partOfSpeech").trim();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            new Driver();
            connection = DB.getConnection();
            String sql = "INSERT INTO db_dictionary.dictionary VALUE (NULL ,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, english);
            preparedStatement.setString(2, chinese);
            preparedStatement.setString(3, phonetic);
            preparedStatement.setString(4, partOfSpeech);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(null, preparedStatement, connection);
        }
        response.sendRedirect("/dictionary?action=query"); // 重定向 redirect
    }

    private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DB.getConnection();
            String sql = "SELECT * FROM db_dictionary.dictionary ORDER BY id";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            List<Dictionary> dictionaries = new ArrayList<>();
            while (resultSet.next()) {
                Dictionary dictionary = new Dictionary(
                        resultSet.getInt("id"),
                        resultSet.getString("english"),
                        resultSet.getString("chinese"),
                        resultSet.getString("phonetic"),
                        resultSet.getString("part_of_speech")
                );
                dictionaries.add(dictionary);
            }
            request.getSession().setAttribute("dictionaries", dictionaries);
            response.sendRedirect("home.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(resultSet, preparedStatement, connection);
        }
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter("id"));

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DB.getConnection();
            String sql = "SELECT * FROM db_dictionary.dictionary WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Dictionary dictionary = new Dictionary(
                    resultSet.getInt("id"),
                    resultSet.getString("english"),
                    resultSet.getString("chinese"),
                    resultSet.getString("phonetic"),
                    resultSet.getString("part_of_speech")
            );
            request.getSession().setAttribute("dictionary", dictionary);
            response.sendRedirect("edit.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(resultSet, preparedStatement, connection);
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter("id"));
        String chinese = request.getParameter("chinese").trim();
        String english = request.getParameter("english").trim();
        String phonetic = request.getParameter("phonetic").trim();
        String partOfSpeech = request.getParameter("partOfSpeech").trim();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            new Driver();
            connection = DB.getConnection();
            String sql = "UPDATE db_dictionary.dictionary SET english = ?, chinese = ?, phonetic = ?, part_of_speech = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, english);
            preparedStatement.setString(2, chinese);
            preparedStatement.setString(3, phonetic);
            preparedStatement.setString(4, partOfSpeech);
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(null, preparedStatement, connection);
        }
        response.sendRedirect("/dictionary?action=query"); // 重定向 redirect
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter("id"));
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            new Driver();
            connection = DB.getConnection();
            String sql = "DELETE FROM db_dictionary.dictionary WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(null, preparedStatement, connection);
        }
        response.sendRedirect("/dictionary?action=query"); // 重定向 redirect
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
