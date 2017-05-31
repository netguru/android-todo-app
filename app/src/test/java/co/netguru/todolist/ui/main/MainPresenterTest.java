package co.netguru.todolist.ui.main;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import co.netguru.todolist.RxSchedulersOverrideRule;

public class MainPresenterTest {

    @Rule
    public final RxSchedulersOverrideRule rxSchedulersOverrideRule = new RxSchedulersOverrideRule();

    @Mock
    private MainView mvpView;

    @InjectMocks
    private MainPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter.onAttach(mvpView);
    }

    @Test
    public void shouldShowTaskCreationWhenHandlingAddTaskClick() throws Exception {
        //when
        presenter.handleAddTaskClick();
        //then
        verify(mvpView).showAddTaskView();
    }
}